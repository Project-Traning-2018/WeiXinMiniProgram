package com.byene.Controller;

import com.byene.Conversion.GetWxInfo;
import com.byene.Conversion.InfoMd5;
import com.byene.Dao.UserInfo;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.UserInfo2Back;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.UserInfoServiceImpl;
import com.byene.Utils.JsonUtils;
import com.byene.Enums.WxInfoStausEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author byene
 * @date 2019/1/8 8:44 PM
 */
@RestController
@RequestMapping( "/wx" )
@Slf4j
public class WxController {

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetWxInfo getWxInfo;

    @Autowired
    InfoMd5 infoMd5;

    @PostMapping( "/wxlogin" )
    public ResultVO WxLogin( @RequestParam("code") String code )
    {
        /*从微信服务器端获取用户openid和session_key*/
        log.info( "前端code值:  " + code );
        WxInfo wxInfo = getWxInfo.Get( code );
        log.info( "用户openId值:  " + wxInfo.getOpenid() );
        log.info( "用户session_key值:  " + wxInfo.getSession_key() );

        /*使用MD5算法对用户openid和session_key进行加密*/
        String wxInfoMd5 = infoMd5.getMd5( wxInfo );
        log.info( "加密后的Md5值:  " + wxInfoMd5 );
        /*将wxInfoMd5值作为key, 用户openid和session_key作为value, 有效期为100分钟,存入Redis中*/
        strRedis.opsForValue().set( wxInfoMd5, JsonUtils.objectToJson( wxInfo ),60*100, TimeUnit.SECONDS );

        /*将wxInfoMd5值封装好,作为用户登录凭证,返回给前端*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode( WxInfoStausEnum.WX_SUCCESS.getCode() );
        resultVO.setMsg( WxInfoStausEnum.WX_SUCCESS.getMessage() );
        resultVO.setData( wxInfoMd5 );

        return resultVO;
    }

    @PostMapping( value = { "/wxuserinfo", "/wxuserinfoupdate" } )
    public ResultVO WxUserinfo(@RequestBody UserInfo2Back userInfo2Back )
    {
        log.info( "用户信息表段:  " + userInfo2Back.toString() );
        ResultVO resultVO = new ResultVO();
        /*在redis中查询userKey*/
        String userKey = userInfo2Back.getUserIdMd5();
        log.info( "userKey值:  " + userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
        {
            resultVO.setCode( WxInfoStausEnum.WX_ERROR.getCode() );
            resultVO.setMsg( WxInfoStausEnum.WX_ERROR.getMessage() );
            return resultVO;
        }

        /*userKey未过期,将openid和用户信息存储到mysql中*/
        UserInfo userInfo = new UserInfo();
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        userInfo.setUserId( Wxresult.getOpenid() );
        userInfo.setUserName( userInfo2Back.getUserName() );
        userInfo.setUserIcon( userInfo2Back.getUserIcon() );
        userInfo.setUserPhonenumber( userInfo2Back.getUserPhonenumber() );
        userInfoService.save( userInfo );

        /*返回用户信息更新成功*/
        resultVO.setCode( WxInfoStausEnum.WX_UPDATE.getCode() );
        resultVO.setMsg( WxInfoStausEnum.WX_UPDATE.getMessage() );
        return resultVO;
    }

    @PostMapping( "/getuserinfo" )
    public ResultVO GetUserInfo( @RequestParam("userKey") String userKey )
    {
        log.info( "UserKey:  "+userKey );
        ResultVO resultVO = new ResultVO();

        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
        {
            resultVO.setCode( WxInfoStausEnum.WX_ERROR.getCode() );
            resultVO.setMsg( WxInfoStausEnum.WX_ERROR.getMessage() );
            return resultVO;
        }

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String userId = Wxresult.getOpenid();

        UserInfo userInfo = userInfoService.FindOneById( userId );

        log.info( "用户信息： " + userInfo.toString() );

        resultVO.setCode( WxInfoStausEnum.WX_GET.getCode() );
        resultVO.setMsg( WxInfoStausEnum.WX_GET.getMessage() );
        resultVO.setData( userInfo );

        log.info( "resultVO:  " + resultVO.toString() );

        return resultVO;
    }

}
