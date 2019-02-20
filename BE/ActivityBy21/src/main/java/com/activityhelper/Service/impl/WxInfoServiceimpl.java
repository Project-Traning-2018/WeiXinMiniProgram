package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Conversion.GetWxInfo;
import com.activityhelper.Conversion.InfoMd5;
import com.activityhelper.Dao.UserInfo;
import com.activityhelper.Enums.WxInfoStausEnum;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.UserInfo2Back;
import com.activityhelper.Pojo.WxInfo;
import com.activityhelper.Service.WxInfoService;
import com.activityhelper.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author byene
 * @date 2019/2/10 6:29 AM
 */
@Service
@Slf4j
public class WxInfoServiceimpl implements WxInfoService {

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetWxInfo getWxInfo;

    @Autowired
    InfoMd5 infoMd5;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Autowired
    UserInfoServiceimpl userInfoService;

    @Override
    public ResultVO Wxlogin(String code) {
        /*从微信服务器端获取用户openid和session_key*/
        WxInfo wxInfo = getWxInfo.Get( code );

        log.info("[前端code值]          [{}]", code );
        log.info("[用户openId值]        [{}]", wxInfo.getOpenid() );
        log.info("[用户session_key值]   [{}]", wxInfo.getSession_key() );

        /*使用MD5算法对用户openid和session_key进行加密*/
        String wxInfoMd5 = infoMd5.getMd5( wxInfo );

        log.info( "[加密后的Md5值]       [{}]", wxInfoMd5 );

        /*将wxInfoMd5值作为key, 用户openid和session_key作为value, 有效期为100分钟,存入Redis中*/
        strRedis.opsForValue().set( wxInfoMd5, JsonUtils.objectToJson( wxInfo ),60*100, TimeUnit.SECONDS );

        /*将wxInfoMd5值封装好,作为用户登录凭证,返回给前端*/
        return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_SUCCESS.getCode(), WxInfoStausEnum.WX_SUCCESS.getMessage(), wxInfoMd5 );
    }


    @Override
    public ResultVO WxUserinfo(UserInfo2Back userInfo2Back) {

        log.info("[用户信息表段]         [{}]", userInfo2Back.toString() );

        /*获取用户md5值*/
        String userKey = userInfo2Back.getUserIdMd5();

        log.info( "[userKey值]         [{}]", userKey );

        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        log.info( "[Wxresult]         [{}]", Wxresult.toString() );

        String UserKey = Wxresult.getOpenid();

        UserInfo userInfo = new UserInfo();

        List< UserInfo > userInfoList = userInfoService.FindAll();

        Integer flag = 0;

        for( UserInfo key : userInfoList  )
        {
            if( key.getUserId().equals( UserKey ) )
            {
                userInfo = key;
                flag = 1;
                break;
            }
        }

        if( flag.equals( 0 )  )
        {
            userInfo.setUserId( Wxresult.getOpenid() );
            userInfo.setUserName( userInfo2Back.getUserName() );
            userInfo.setUserIcon( userInfo2Back.getUserIcon() );
            userInfo.setUserPhonenumber( userInfo2Back.getUserPhonenumber() );
        }
        else
        {
            userInfo.setUserName( userInfo2Back.getUserName() );
            userInfo.setUserIcon( userInfo2Back.getUserIcon() );
        }

        log.info( "[userInfo]         [{}]", userInfo.toString() );
        userInfoService.save( userInfo );

        /*返回用户信息保存成功*/
        return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_SAVE.getCode(), WxInfoStausEnum.WX_SAVE.getMessage(), null );

    }


    @Override
    public ResultVO WxUserinfoUpdate(UserInfo2Back userInfo2Back) {

        log.info("[用户信息表段]         [{}]", userInfo2Back.toString() );

        /*获取用户md5值*/
        String userKey = userInfo2Back.getUserIdMd5();

        log.info( "[userKey值]         [{}]", userKey );

        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*userKey未过期,将openid和用户信息存储到mysql中*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String UserKey = Wxresult.getOpenid();

        UserInfo userInfo = userInfoService.FindOneById( UserKey );

        userInfo.setUserPhonenumber( userInfo2Back.getUserPhonenumber() );
        userInfoService.save( userInfo );

        /*返回用户信息更新成功*/
        return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_UPDATE.getCode(), WxInfoStausEnum.WX_UPDATE.getMessage(), null );

    }

    @Override
    public ResultVO GetUserInfo(String userKey) {

        log.info( "[userKey值]         [{}]", userKey );

        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String userId = Wxresult.getOpenid();

        UserInfo userInfo = userInfoService.FindOneById( userId );

        log.info( "[用户信息]         [{}]", userInfo.toString() );

        return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_GET.getCode(), WxInfoStausEnum.WX_GET.getMessage(), userInfo  );

    }
}
