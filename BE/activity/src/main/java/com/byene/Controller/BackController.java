package com.byene.Controller;

import com.byene.Dao.ActivityInfo;
import com.byene.Dao.UserInfo;
import com.byene.Dao.UserSuggestion;
import com.byene.Enums.BackInfoStausEnum;
import com.byene.Pojo.Page.BackPage2Back;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.Suggestion.SuggestionDelete2Back;
import com.byene.Service.impl.ActivityInfoServiceImpl;
import com.byene.Service.impl.UserInfoServiceImpl;
import com.byene.Service.impl.UserSuggestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/9 10:37 AM
 */

@RestController
@RequestMapping( "/back" )
public class BackController {

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    UserSuggestionServiceImpl userSuggestionService;

    @Autowired
    ActivityInfoServiceImpl activityInfoService;

    @PostMapping( "/userinfolistbypage" )
    public ResultVO GetUserinfobypage(@RequestBody BackPage2Back backPage2Back )
    {

        ResultVO resultVO = new ResultVO();

        Integer page = backPage2Back.getPage();
        Integer size = backPage2Back.getSize();

        List< UserInfo > userInfoList = userInfoService.FindAllbyPage( page, size );
        if( userInfoList.isEmpty() )
        {
            resultVO.setCode( BackInfoStausEnum.BACK_EMPTY.getCode() );
            resultVO.setMsg( BackInfoStausEnum.BACK_EMPTY.getMessage() );
            return resultVO;
        }

        resultVO.setCode( BackInfoStausEnum.BACK_USER_SUCCESS.getCode() );
        resultVO.setMsg( BackInfoStausEnum.BACK_USER_SUCCESS.getMessage() );
        resultVO.setData( userInfoList );
        return resultVO;
    }

    @PostMapping( "/userinfolist" )
    public ResultVO GetUserinfo( )
    {
        ResultVO resultVO = new ResultVO();
        List< UserInfo > userInfoList = userInfoService.FindAll();

        resultVO.setCode( BackInfoStausEnum.BACK_USER_SUCCESS.getCode() );
        resultVO.setMsg( BackInfoStausEnum.BACK_USER_SUCCESS.getMessage() );
        resultVO.setData( userInfoList );
        return resultVO;
    }


    @PostMapping( "/activityinfolist" )
    public ResultVO GetActivityInfoList( )
    {
        ResultVO resultVO = new ResultVO();

        List<ActivityInfo> activityInfoList = activityInfoService.findall();

        resultVO.setCode( BackInfoStausEnum.BACK_ACTIVITY_SUCCESS.getCode() );
        resultVO.setMsg( BackInfoStausEnum.BACK_ACTIVITY_SUCCESS.getMessage() );
        resultVO.setData( activityInfoList );
        return resultVO;
    }

    @PostMapping( "/suggestioninfolist" )
    public ResultVO GetSuggestionInfo( )
    {
        ResultVO resultVO = new ResultVO();
        List< UserSuggestion > userSuggestionList = userSuggestionService.FindAll();

        resultVO.setCode( BackInfoStausEnum.BACK_SUGGESTION_SUCCESS.getCode() );
        resultVO.setMsg( BackInfoStausEnum.BACK_SUGGESTION_SUCCESS.getMessage() );
        resultVO.setData( userSuggestionList );
        return resultVO;
    }

    @PostMapping( "/suggestioninfolistbypage" )
    public ResultVO GetSuggestionInfobypage( @RequestBody BackPage2Back backPage2Back )
    {
        ResultVO resultVO = new ResultVO();

        Integer page = backPage2Back.getPage();
        Integer size = backPage2Back.getSize();

        List< UserSuggestion >  userSuggestionList = userSuggestionService.FindAllbyPage( page, size );
        if( userSuggestionList.isEmpty() )
        {
            resultVO.setCode( BackInfoStausEnum.BACK_EMPTY.getCode() );
            resultVO.setMsg( BackInfoStausEnum.BACK_EMPTY.getMessage() );
            return resultVO;
        }

        resultVO.setCode( BackInfoStausEnum.BACK_SUGGESTION_SUCCESS.getCode() );
        resultVO.setMsg( BackInfoStausEnum.BACK_SUGGESTION_SUCCESS.getMessage() );
        resultVO.setData( userSuggestionList );
        return resultVO;
    }

    @PostMapping( "/suggestiondelete" )
    public ResultVO DeleteSuggestionInfo( @RequestBody SuggestionDelete2Back suggestionDelete2Back )
    {
        ResultVO resultVO = new ResultVO();

        Integer suggestionId = suggestionDelete2Back.getSuggestionId();

        userSuggestionService.delete( suggestionId );

        resultVO.setCode( BackInfoStausEnum.BACK_SUGGESTION_DELETE_SUCCESS.getCode() );
        resultVO.setMsg( BackInfoStausEnum.BACK_SUGGESTION_DELETE_SUCCESS.getMessage() );
        return resultVO;
    }
}
