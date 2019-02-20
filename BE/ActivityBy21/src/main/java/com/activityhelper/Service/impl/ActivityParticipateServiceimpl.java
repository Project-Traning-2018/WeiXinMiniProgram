package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Dao.ActivityInfo;
import com.activityhelper.Dao.ActivityMember;
import com.activityhelper.Dao.UserparticipationInfo;
import com.activityhelper.Enums.ActivityInfoStatusEnum;
import com.activityhelper.Enums.UserparticipationStatusEnum;
import com.activityhelper.Enums.WxInfoStausEnum;
import com.activityhelper.Pojo.ActivityChooseInfo2Back;
import com.activityhelper.Pojo.ActivityParticipationInfo2Front;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.WxInfo;
import com.activityhelper.Service.ActivityParticipateService;
import com.activityhelper.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/17 9:39 PM
 */

@Service
@Slf4j
public class ActivityParticipateServiceimpl implements ActivityParticipateService {

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Autowired
    ActivityInfoServiceimpl  activityInfoService;

    @Autowired
    UserparticipationInfoServiceimpl userparticipationInfoService;

    @Autowired
    ActivityMemberServiceImpl activityMemberService;


    @Override
    @Transactional
    public ResultVO GetActivityInfoParticipateInfo(String userKey, Integer activityId) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        ActivityInfo activityInfo = activityInfoService.FindOnebyId( activityId );

        ActivityParticipationInfo2Front activityParticipationInfo2Front = new ActivityParticipationInfo2Front();

        activityParticipationInfo2Front.setActivityId( activityId );
        activityParticipationInfo2Front.setActivityparticipationItem1( activityInfo.getActivityparticipationItem1() );
        activityParticipationInfo2Front.setActivityparticipationItem2( activityInfo.getActivityparticipationItem2() );
        activityParticipationInfo2Front.setActivityparticipationItem3( activityInfo.getActivityparticipationItem3() );
        activityParticipationInfo2Front.setActivityparticipationItem4( activityInfo.getActivityparticipationItem4() );
        activityParticipationInfo2Front.setActivityparticipationItem5( activityInfo.getActivityparticipationItem5() );
        activityParticipationInfo2Front.setActivityparticipationItem6( activityInfo.getActivityparticipationItem6() );
        activityParticipationInfo2Front.setActivityparticipationItem7( activityInfo.getActivityparticipationItem7() );
        activityParticipationInfo2Front.setActivityparticipationItem8( activityInfo.getActivityparticipationItem8() );
        activityParticipationInfo2Front.setActivityparticipationItem9( activityInfo.getActivityparticipationItem9() );
        activityParticipationInfo2Front.setActivityparticipationItem10( activityInfo.getActivityparticipationItem10() );

        return getResultVOInfo.GetResultVO(ActivityInfoStatusEnum.ACTIVITY_PARTICIPATEINFO_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_PARTICIPATEINFO_SUCCESS.getMessage(), activityParticipationInfo2Front );

    }

    @Override
    @Transactional
    public ResultVO ActivityParticipationInfo(ActivityChooseInfo2Back activityChooseInfo2Back) {

        String userKey = activityChooseInfo2Back.getUserIdMd5();

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        /*获取活动信息*/
        Integer activityId = activityChooseInfo2Back.getActivityId();
        ActivityInfo Activityresult = activityInfoService.FindOnebyId( activityId );

        /*活动已过期*/
        if( Activityresult.getActivityValid().equals( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode() ) )
            return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode(), ActivityInfoStatusEnum.ACTIVITY_INVALID.getMessage(), null );

        /*活动已删除*/
        if( Activityresult.getActivityValid().equals( ActivityInfoStatusEnum.ACTIVITY_STATUS_DELETE.getCode() ) )
            return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode(), ActivityInfoStatusEnum.ACTIVITY_INVALID.getMessage(), null );

        /*活动人数已满*/
        if( Activityresult.getActivityPeopleregistered().equals( Activityresult.getActivityPeoplelimit() ) )
            return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_PEOPLE_FULL.getCode(), ActivityInfoStatusEnum.ACTIVITY_PEOPLE_FULL.getMessage(), null );

        String UserId = Wxresult.getOpenid();
        Integer ActivityId = Activityresult.getActivityId();

        /*无法参加自己发布的活动*/
        if( Activityresult.getActivityOrganizerid().equals( UserId ) )
            return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_ORGANIZER_ERROR.getCode(), ActivityInfoStatusEnum.ACTIVITY_ORGANIZER_ERROR.getMessage() , null );

        /*无法参加已报名活动*/
        List<ActivityMember> activityMemberList = activityMemberService.findallByActivityId( ActivityId );

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
            return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_ERROR.getCode(), ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_ERROR.getMessage(), null );

        /*活动人数加1*/
        Activityresult.setActivityPeopleregistered( Activityresult.getActivityPeopleregistered() + 1 );
        activityInfoService.save( Activityresult );

        /*添加活动参与人对应表信息*/
        ActivityMember activityMember = new ActivityMember();
        activityMember.setActivityUserid( UserId );
        activityMember.setActivityActivityid( ActivityId );
        activityMemberService.save( activityMember );

        UserparticipationInfo userparticipationInfo = new UserparticipationInfo();

        userparticipationInfo.setUserparticipationActivityid( activityChooseInfo2Back.getActivityId() );
        userparticipationInfo.setUserparticipationUserid( Wxresult.getOpenid() );
        userparticipationInfo.setUserparticipationItem1( activityChooseInfo2Back.getActivityparticipationItem1() );
        userparticipationInfo.setUserparticipationItem2( activityChooseInfo2Back.getActivityparticipationItem2() );
        userparticipationInfo.setUserparticipationItem3( activityChooseInfo2Back.getActivityparticipationItem3() );
        userparticipationInfo.setUserparticipationItem4( activityChooseInfo2Back.getActivityparticipationItem4() );
        userparticipationInfo.setUserparticipationItem5( activityChooseInfo2Back.getActivityparticipationItem5() );
        userparticipationInfo.setUserparticipationItem6( activityChooseInfo2Back.getActivityparticipationItem6() );
        userparticipationInfo.setUserparticipationItem7( activityChooseInfo2Back.getActivityparticipationItem7() );
        userparticipationInfo.setUserparticipationItem8( activityChooseInfo2Back.getActivityparticipationItem8() );
        userparticipationInfo.setUserparticipationItem9( activityChooseInfo2Back.getActivityparticipationItem9() );
        userparticipationInfo.setUserparticipationItem10( activityChooseInfo2Back.getActivityparticipationItem10() );

        userparticipationInfoService.save( userparticipationInfo );

        return getResultVOInfo.GetResultVO(UserparticipationStatusEnum.USERPARTICIPATION_SAVE_SUCCESS.getCode(), UserparticipationStatusEnum.USERPARTICIPATION_SAVE_SUCCESS.getMessage(), null );

    }

    @Override
    @Transactional
    public ResultVO ActivityInfopParticipateCancel(String userKey, Integer activityId) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String Userid = Wxresult.getOpenid();

        /*找寻当前活动*/
        ActivityInfo activityInfo = activityInfoService.FindOnebyId( activityId );
        /*当前活动报名人数减1*/
        activityInfo.setActivityPeopleregistered( activityInfo.getActivityPeopleregistered() - 1 );
        /*更新活动*/
        activityInfoService.save( activityInfo );

        /*删除用户活动对应表*/
        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( activityId );

        for( ActivityMember key: activityMemberList )
        {
            if( key.getActivityUserid().equals( Userid ) )
            {
                activityMemberService.delete( key );
                break;
            }
        }

        /*删除用户参加活动信息表*/
        UserparticipationInfo userparticipationInfo = userparticipationInfoService.findone( activityId, Userid );

        userparticipationInfoService.delete( userparticipationInfo );

        return getResultVOInfo.GetResultVO( UserparticipationStatusEnum.USERPARTICIPATION_DELETE_SUCCESS.getCode(), UserparticipationStatusEnum.USERPARTICIPATION_DELETE_SUCCESS.getMessage(), null );
    }

    @Override
    @Transactional
    public ResultVO ActivityInfoParticipateList(String userKey) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String Userid = Wxresult.getOpenid();

        List< ActivityMember > activityMemberList = activityMemberService.findallByUserId( Userid );
        List< ActivityInfo > activityInfoList = new ArrayList<>();

        for( ActivityMember key: activityMemberList )
        {
            ActivityInfo activityInfoResult = activityInfoService.FindOnebyId( key.getActivityActivityid() );
            activityInfoList.add( activityInfoResult );
        }

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_LIST_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_PARTICIPATE_LIST_SUCCESS.getMessage(), activityInfoList );

    }

    @Override
    @Transactional
    public ResultVO ActivityInfoParticipationUpdate(ActivityChooseInfo2Back activityChooseInfo2Back) {

        String userKey = activityChooseInfo2Back.getUserIdMd5();

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String Userid = Wxresult.getOpenid();

        Integer Activityid = activityChooseInfo2Back.getActivityId();

        List< UserparticipationInfo > userparticipationInfoList = userparticipationInfoService.findAllByUserparticipationActivityid( Activityid );

        UserparticipationInfo NewuserparticipationInfo = new UserparticipationInfo();

        for( UserparticipationInfo key: userparticipationInfoList )
        {
            if( key.getUserparticipationUserid().equals( Userid ) )
            {
                NewuserparticipationInfo = key;
                break;
            }
        }

        NewuserparticipationInfo.setUserparticipationItem1( activityChooseInfo2Back.getActivityparticipationItem1() );
        NewuserparticipationInfo.setUserparticipationItem2( activityChooseInfo2Back.getActivityparticipationItem2() );
        NewuserparticipationInfo.setUserparticipationItem3( activityChooseInfo2Back.getActivityparticipationItem3() );
        NewuserparticipationInfo.setUserparticipationItem4( activityChooseInfo2Back.getActivityparticipationItem4() );
        NewuserparticipationInfo.setUserparticipationItem5( activityChooseInfo2Back.getActivityparticipationItem5() );
        NewuserparticipationInfo.setUserparticipationItem6( activityChooseInfo2Back.getActivityparticipationItem6() );
        NewuserparticipationInfo.setUserparticipationItem7( activityChooseInfo2Back.getActivityparticipationItem7() );
        NewuserparticipationInfo.setUserparticipationItem8( activityChooseInfo2Back.getActivityparticipationItem8() );
        NewuserparticipationInfo.setUserparticipationItem9( activityChooseInfo2Back.getActivityparticipationItem9() );
        NewuserparticipationInfo.setUserparticipationItem10( activityChooseInfo2Back.getActivityparticipationItem10() );

        userparticipationInfoService.save( NewuserparticipationInfo );

        return getResultVOInfo.GetResultVO( UserparticipationStatusEnum.USERPARTICIPATION_UPDATE_SUCCESS.getCode(), UserparticipationStatusEnum.USERPARTICIPATION_UPDATE_SUCCESS.getMessage(), null );
    }
}
