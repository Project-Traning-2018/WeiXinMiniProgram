package com.byene.Controller;

import com.byene.Conversion.ActivityInfo2BacktoActivityInfo;
import com.byene.Conversion.CalculateDistance;
import com.byene.Conversion.UserInfo2UserInfoToFront;
import com.byene.Dao.ActivityCollect;
import com.byene.Dao.ActivityInfo;
import com.byene.Dao.ActivityMember;
import com.byene.Dao.UserInfo;
import com.byene.Enums.ActivityInfoStatusEnum;
import com.byene.Enums.WxInfoStausEnum;
import com.byene.Pojo.Activity.ActivityInfo2Back;
import com.byene.Pojo.MapInfo2Back;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.UserInfoToFront;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.ActivityCollectServiceImpl;
import com.byene.Service.impl.ActivityInfoServiceImpl;
import com.byene.Service.impl.ActivityMemberServiceImpl;
import com.byene.Service.impl.UserInfoServiceImpl;
import com.byene.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布活动相关操作
 * @author byene
 * @date 2019/1/11 11:27 AM
 */
@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityController {

    @Autowired
    ActivityInfoServiceImpl activityInfoService;

    @Autowired
    ActivityMemberServiceImpl activityMemberService;

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    ActivityInfo2BacktoActivityInfo activityInfo2BacktoActivityInfo;

    @Autowired
    CalculateDistance calculateDistance;

    @Autowired
    UserInfo2UserInfoToFront userInfo2UserInfoToFront;

    @Autowired
    ActivityCollectServiceImpl activityCollectService;


    public static double R = 2.0;

    /*保存活动信息*/
    @PostMapping( "/activityinfo/save" )
    public ResultVO ActivityInfo( @RequestBody ActivityInfo2Back activityInfo2Back )
    {
        log.info( "前端活动信息： " + activityInfo2Back.toString() );
        ResultVO resultVO = new ResultVO();
        /*在redis中查询userKey*/
        String userKey = activityInfo2Back.getUserIdMd5();
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

        ActivityInfo activityInfo = activityInfo2BacktoActivityInfo.Transfer( Wxresult, activityInfo2Back );


        activityInfo.setActivityPeopleregistered( activityInfo.getActivityPeopleregistered() + 1 );

        ActivityMember activityMember = new ActivityMember();
        activityMember.setActivityUserid( activityInfo.getActivityOrganizerid() );
        activityMember.setActivityActivityid( activityInfo.getActivityId() );
        activityMemberService.save( activityMember );

        log.info( "活动信息： " + activityInfo.toString() );
        activityInfoService.save( activityInfo );



        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_ADD_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_ADD_SUCCESS.getMessage() );

        return resultVO;
    }

    /*删除已发布活动*/
    @PostMapping( "/activityinfo/deletebyorganizer" )
    public ResultVO ActivityInfoDeletebyOrganizer( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId )
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

        activityInfoService.delete( activityId );

        /*删除活动成员信息*/
        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( activityId );

        for( ActivityMember key: activityMemberList )
        {
            activityMemberService.delete( key );
        }

        /*删除活动收藏信息*/
        List< ActivityCollect > activityCollectList = activityCollectService.FindallByActivityId( activityId );

        for( ActivityCollect key: activityCollectList )
        {
            activityCollectService.delete( key );
        }

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_DELETE_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_DELETE_SUCCESS.getMessage() );
        return resultVO;
    }

    /*查询已发布活动列表*/
    @PostMapping( "/activityinfo/listbyid" )
    public ResultVO ActivityInfoListByid(  @RequestParam("userKey") String userKey )
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

        List< ActivityInfo > activityInfoList = activityInfoService.FindAllbyId( userId );

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_SEARCH_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_SEARCH_SUCCESS.getMessage() );
        resultVO.setData( activityInfoList );
        return resultVO;
    }

    /*查询附近已发布活动*/
    @PostMapping( "/activityinfo/getactivitynearby" )
    public ResultVO GetActivityNearby( @RequestBody MapInfo2Back mapinfo2Back )
    {
        ResultVO resultVO = new ResultVO();
        log.info( "地图信息： " + mapinfo2Back.toString() );

        String userKey = mapinfo2Back.getUserIdMd5();
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
        {
            resultVO.setCode( WxInfoStausEnum.WX_ERROR.getCode() );
            resultVO.setMsg( WxInfoStausEnum.WX_ERROR.getMessage() );
            return resultVO;
        }


        BigDecimal UserLongitude = mapinfo2Back.getActivityLongitude();
        BigDecimal UserLatitude = mapinfo2Back.getActivityLatitude();
        List< ActivityInfo > activityInfoList = activityInfoService.findall();
        List< ActivityInfo > activityInfoValidList = new ArrayList<>();

        for( ActivityInfo key : activityInfoList )
        {
            double dis = calculateDistance.getDistance( UserLatitude.doubleValue(), UserLongitude.doubleValue(), key.getActivityLatitude().doubleValue(), key.getActivityLongitude().doubleValue() );
            if( dis <= R )
            {
                activityInfoValidList.add( key );
            }
        }

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_NEARBY_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_NEARBY_SUCCESS.getMessage() );
        resultVO.setData( activityInfoValidList );
        return resultVO;
    }

    /*查询活动已报名人数列表*/
    @PostMapping( "/activityinfo/peopleregisteredlist" )
    public ResultVO GetPeopleRegisteredList( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId )
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

        List<UserInfoToFront> userInfoToFrontList = new ArrayList<>();

        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( activityId );

        for( ActivityMember key: activityMemberList )
        {
            String userId = key.getActivityUserid();
            UserInfo userInfo = userInfoService.FindOneById( userId );
            userInfoToFrontList.add( userInfo2UserInfoToFront.UserInfoTransfer( userInfo ) );
        }

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_PEOPLEREGISTERED_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_PEOPLEREGISTERED_SUCCESS.getMessage() );
        resultVO.setData( userInfoToFrontList );
        return resultVO;
    }
}
