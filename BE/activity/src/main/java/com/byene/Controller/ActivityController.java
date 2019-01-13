package com.byene.Controller;

import com.byene.Conversion.TimeTransfer;
import com.byene.Dao.ActivityInfo;
import com.byene.Dao.ActivityMember;
import com.byene.Enums.ActivityInfoStatusEnum;
import com.byene.Enums.WxInfoStausEnum;
import com.byene.Pojo.Activity.ActivityChooseInfo2Back;
import com.byene.Pojo.Activity.ActivityInfo2Back;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.ActivityInfoServiceImpl;
import com.byene.Service.impl.ActivityMemberServiceImpl;
import com.byene.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
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
    StringRedisTemplate strRedis;

    @Autowired
    TimeTransfer timeTransfer;

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

        /*将activityInfo2Back的值复制到ActivityInfo中*/
        ActivityInfo activityInfo = new ActivityInfo();

        /*活动类型*/
        activityInfo.setActivityType( activityInfo2Back.getActivityType() );

        /*活动主题*/
        activityInfo.setActivitySubject( activityInfo2Back.getActivitySubject() );

        /*活动内容*/
        activityInfo.setActivityContent( activityInfo2Back.getActivityContent() );

        /*活动报名开始时间*/
        activityInfo.setActivitySignstartdate( timeTransfer.String2Timestamp( activityInfo2Back.getActivitySignstartdate() ) );

        /*活动报名结束时间*/
        activityInfo.setActivitySignenddate( timeTransfer.String2Timestamp( activityInfo2Back.getActivitySignenddate() ) );

        /*活动开始时间*/
        activityInfo.setActivityStartdate( timeTransfer.String2Timestamp( activityInfo2Back.getActivityStartdate() ) );

        /*活动结束时间*/
        activityInfo.setActivityEnddate( timeTransfer.String2Timestamp( activityInfo2Back.getActivityEnddate() ) );

        /*活动费用*/
        activityInfo.setActivityFee( activityInfo2Back.getActivityFee() );

        /*活动总人数*/
        activityInfo.setActivityPeoplelimit( activityInfo2Back.getActivityPeoplelimit() );

        /*活动地址*/
        activityInfo.setActivityAddress( activityInfo2Back.getActivityAddress() );

        /*活动地址名称*/
        activityInfo.setActivityAddressname( activityInfo2Back.getActivityAddressname() );

        /*活动经度*/
        //log.info( "转换前： " +  activityInfo2Back.getActivityLatitude() );
        //BigDecimal bd = new BigDecimal( activityInfo2Back.getActivityLatitude()  );
        //log.info( "转换后： " + bd );
        activityInfo.setActivityLatitude( new BigDecimal( activityInfo2Back.getActivityLatitude()  ));

        /*活动纬度*/
        activityInfo.setActivityLongitude( new BigDecimal( activityInfo2Back.getActivityLongitude() ) );

        /*活动发起人*/
        activityInfo.setActivityOrganizer( activityInfo2Back.getActivityOrganizer() );

        /*活动发起人手机号*/
        activityInfo.setActivityOrganizerphonenumber( activityInfo2Back.getActivityOrganizerphonenumber() );

        /*活动发起人openid*/
        activityInfo.setActivityOrganizerid( Wxresult.getOpenid() );

        log.info( "活动信息： " + activityInfo.toString() );

        activityInfoService.save( activityInfo );

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_ADD_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_ADD_SUCCESS.getMessage() );
        resultVO.setData( activityInfo );
        return resultVO;
    }

    @PostMapping( "/activityinfo/deletebyorganizer" )
    public ResultVO ActivityInfoDeletebyOrganizer( String userKey, Integer acitivityId )
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

        activityInfoService.delete( acitivityId );

        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( acitivityId );

        for( ActivityMember key: activityMemberList )
        {
            activityMemberService.delete( key );
        }

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_DELETE_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_DELETE_SUCCESS.getMessage() );
        return resultVO;
    }

    @PostMapping( "/activityinfo/participatecancel" )
    public ResultVO ActivityInfopParticipateCancel( String userKey, Integer acitivityId  )
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

        ActivityInfo activityInfo = activityInfoService.FindOnebyId( acitivityId );
        activityInfo.setActivityPeopleregistered( activityInfo.getActivityPeopleregistered() - 1 );
        activityInfoService.save( activityInfo );

        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( acitivityId );

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

    @PostMapping( "/activityinfo/listbyid" )
    public ResultVO ActivityInfoListByid( String userKey )
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

}
