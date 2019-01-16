package com.byene.Controller;

import com.byene.Conversion.ActivityInfo2BacktoActivityInfo;
import com.byene.Conversion.CalculateDistance;
import com.byene.Dao.ActivityInfo;
import com.byene.Dao.ActivityMember;
import com.byene.Enums.ActivityInfoStatusEnum;
import com.byene.Enums.WxInfoStausEnum;
import com.byene.Pojo.Activity.ActivityChooseInfo2Back;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.ActivityInfoServiceImpl;
import com.byene.Service.impl.ActivityMemberServiceImpl;
import com.byene.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author byene
 * @date 2019/1/13 4:49 PM
 */
@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityParticipateController {

    @Autowired
    ActivityInfoServiceImpl activityInfoService;

    @Autowired
    ActivityMemberServiceImpl activityMemberService;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    ActivityInfo2BacktoActivityInfo activityInfo2BacktoActivityInfo;

    @Autowired
    CalculateDistance calculateDistance;

    /*取消参加活动*/
    @PostMapping( "/activityinfo/participatecancel" )
    public ResultVO ActivityInfopParticipateCancel(@RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId  )
    {
        ResultVO resultVO = new ResultVO();
        log.info( "userKey值:  " + userKey );
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

        ActivityInfo activityInfo = activityInfoService.FindOnebyId( activityId );
        activityInfo.setActivityPeopleregistered( activityInfo.getActivityPeopleregistered() - 1 );
        activityInfoService.save( activityInfo );

        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( activityId );

        for( ActivityMember key: activityMemberList )
        {
            if( key.getActivityUserid().equals( userId ) )
            {
                activityMemberService.delete( key );
                break;
            }
        }

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_CANCEL_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_CANCEL_SUCCESS.getMessage() );

        return resultVO;
    }

    /*参加活动*/
    @PostMapping( "/activityinfo/participate" )
    public ResultVO ActivityParticipationInfo(@RequestBody ActivityChooseInfo2Back activityChooseInfo2Back )
    {
        ResultVO resultVO = new ResultVO();
        log.info( "用户ID和活动ID： " + activityChooseInfo2Back.toString() );

        String userKey = activityChooseInfo2Back.getUserIdMd5();
        Integer activityId = activityChooseInfo2Back.getActivityId();
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
        {
            resultVO.setCode( WxInfoStausEnum.WX_ERROR.getCode() );
            resultVO.setMsg( WxInfoStausEnum.WX_ERROR.getMessage() );
            return resultVO;
        }

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        /*获取活动信息*/
        ActivityInfo Activityresult = activityInfoService.FindOnebyId( activityId );

        /*活动已过期*/
        if( Activityresult.getActivityValid().equals( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode() ) )
        {
            resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode() );
            resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_INVALID.getMessage() );
            return resultVO;
        }

        /*活动人数已满*/
        if( Activityresult.getActivityPeopleregistered().equals( Activityresult.getActivityPeoplelimit() ) )
        {
            resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_PEOPLE_FULL.getCode() );
            resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_PEOPLE_FULL.getMessage() );
            return resultVO;
        }

        String UserId = Wxresult.getOpenid();
        Integer ActivityId = Activityresult.getActivityId();

        /*无法参加自己发布的活动*/
        if( Activityresult.getActivityOrganizerid().equals( UserId ) )
        {
            resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_ORGANIZER_ERROR.getCode() );
            resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_ORGANIZER_ERROR.getMessage() );
            return resultVO;
        }

        /*无法参加已报名活动*/
        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( ActivityId );

        boolean flag = false;

        for( ActivityMember key: activityMemberList )
        {
            if( key.getActivityUserid().equals( UserId ) )
            {
                flag = true;
                break;
            }
        }

        if( flag )
        {
            resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_ERROR.getCode() );
            resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_ERROR.getMessage() );
            return resultVO;
        }

        /*活动人数加1*/
        Activityresult.setActivityPeopleregistered( Activityresult.getActivityPeopleregistered() + 1 );
        activityInfoService.save( Activityresult );

        /*添加活动参与人对应表信息*/
        ActivityMember activityMember = new ActivityMember();
        activityMember.setActivityUserid( UserId );
        activityMember.setActivityActivityid( ActivityId );
        activityMemberService.save( activityMember );

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_SUCCESS.getMessage() );

        return resultVO;
    }

    /*获取参加活动列表*/
    @PostMapping( "/activityinfo/participatelist" )
    public ResultVO ActivityInfoParticipateList( @RequestParam("userKey") String userKey )
    {
        ResultVO resultVO = new ResultVO();
        log.info( "userKey值:  " + userKey );
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

        List< ActivityMember > activityMemberList = activityMemberService.findallByUserId( userId );
        List< ActivityInfo > activityInfoList = new ArrayList<>();

        for( ActivityMember key: activityMemberList )
        {
            ActivityInfo activityInfoResult = activityInfoService.FindOnebyId( key.getActivityActivityid() );
            activityInfoList.add( activityInfoResult );
        }

        resultVO.setData( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_LIST_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_LIST_SUCCESS.getMessage() );
        resultVO.setData( activityInfoList );

        return resultVO;
    }
}
