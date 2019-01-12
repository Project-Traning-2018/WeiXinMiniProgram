package com.byene.Controller;

import com.byene.Dao.UserSuggestion;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.Suggestion.UserSuggestion2Back;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.UserSuggestionServiceImpl;
import com.byene.Utils.JsonUtils;
import com.byene.Enums.WxInfoStausEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/1/8 9:40 PM
 */
@RestController
@RequestMapping( "/suggestion" )
@Slf4j
public class SuggestionController {

    @Autowired
    UserSuggestionServiceImpl userSuggestionService;

    @Autowired
    StringRedisTemplate strRedis;


    @PostMapping( "/usersuggestion" )
    public ResultVO UserSuggestion(@RequestBody UserSuggestion2Back userSuggestion2Back )
    {
        log.info( "userKey + " + userSuggestion2Back.getUserIdMd5()  );
        log.info( "userSuggestion + " + userSuggestion2Back.getSuggestion() );
        ResultVO resultVO = new ResultVO();
        /*在redis中查询userKey*/
        String userKey = userSuggestion2Back.getUserIdMd5();
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
        {
            resultVO.setCode( WxInfoStausEnum.WX_ERROR.getCode() );
            resultVO.setMsg( WxInfoStausEnum.WX_ERROR.getMessage() );
            return resultVO;
        }

        /*userKey未过期,将openid和建议存储到mysql中*/
        UserSuggestion userSuggestion = new UserSuggestion();
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        userSuggestion.setUserId( Wxresult.getOpenid() );
        userSuggestion.setSuggestionContent( userSuggestion2Back.getSuggestion() );

        userSuggestionService.save( userSuggestion );
        /*返回用户建议提交成功*/
        resultVO.setCode( WxInfoStausEnum.SUGGESTION_SUCCESS.getCode() );
        resultVO.setMsg( WxInfoStausEnum.SUGGESTION_SUCCESS.getMessage() );
        return resultVO;
    }


}
