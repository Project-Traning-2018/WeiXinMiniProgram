package com.activityhelper.Service.impl;

import com.activityhelper.Check.ActivityListCheck;
import com.activityhelper.Conversion.*;
import com.activityhelper.Dao.*;
import com.activityhelper.Enums.ActivityInfoStatusEnum;
import com.activityhelper.Enums.WxInfoStausEnum;
import com.activityhelper.Pojo.*;
import com.activityhelper.Repository.ActivityInfoRepository;
import com.activityhelper.Service.ActivityInfoService;
import com.activityhelper.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/15 8:00 PM
 */
@Service
@Slf4j
public class ActivityInfoServiceimpl implements ActivityInfoService {

    @Autowired
    UserInfoServiceimpl userInfoService;

    @Autowired
    ActivityInfoRepository activityInfoRepository;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Autowired
    ActivityInfo2BacktoActivityInfo activityInfo2BacktoActivityInfo;

    @Autowired
    TimeTransfer timeTransfer;

    @Autowired
    GetFileUrl getFileUrl;

    @Autowired
    CalculateDistance calculateDistance;

    @Autowired
    UserInfo2UserInfoToFront userInfo2UserInfoToFront;

    @Autowired
    InfoMd5 infoMd5;

    @Autowired
    ActivityMemberServiceImpl activityMemberService;

    @Autowired
    ActivityCollectServiceimpl activityCollectService;

    @Autowired
    UserparticipationInfoServiceimpl userparticipationInfoService;

    @Autowired
    ActivityListCheck activityListCheck;

    public static double R = 2.0;

    @Override
    @Transactional
    public void save(ActivityInfo activityInfo) {
        activityInfoRepository.save( activityInfo );
    }


    @Override
    @Transactional
    public ResultVO ActivityInfoSave(ActivityInfo2Back activityInfo2Back) {

        /*在redis中查询userKey*/
        String userKey = activityInfo2Back.getUserIdMd5();

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        ActivityInfo activityInfo = activityInfo2BacktoActivityInfo.Transfer( Wxresult, activityInfo2Back );

        log.info("[活动信息]              [{}]", activityInfo.toString() );

        save( activityInfo );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_ADD_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_ADD_SUCCESS.getMessage(),null );

    }

    @Override
    @Transactional
    public List<ActivityInfo> findall() {
        return activityInfoRepository.findAll();
    }

    @Override
    @Transactional
    public List<ActivityInfo> FindAllbyId(String userId) {
        return activityInfoRepository.findAllByActivityOrganizeridIn( userId );
    }

    @Override
    @Transactional
    public ResultVO ActivityInfoListById(String userKey) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String userId = Wxresult.getOpenid();

        List< ActivityInfo > activityInfoList = FindAllbyId( userId );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_SEARCH_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_SEARCH_SUCCESS.getMessage(), activityInfoList );
    }

    @Override
    @Transactional
    public ActivityInfo FindOnebyId(Integer activityId) {
        return activityInfoRepository.getOne( activityId );
    }

    @Override
    @Transactional
    public ResultVO ActivityInfoUpdate(ActivityInfoUpdate2Back activityInfoUpdate2Back) {

        log.info("[前端更改活动信息]              [{}]", activityInfoUpdate2Back.toString() );

        /*在redis中查询userKey*/
        String userKey = activityInfoUpdate2Back.getUserIdMd5();
        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );
        Integer activityId = activityInfoUpdate2Back.getActivityId();

        ActivityInfo activityInfo = FindOnebyId( activityId );

        /*活动类型*/
        activityInfo.setActivityType( activityInfoUpdate2Back.getActivityType() );

        /*活动主题*/
        activityInfo.setActivitySubject( activityInfoUpdate2Back.getActivitySubject() );

        /*活动内容*/
        activityInfo.setActivityContent( activityInfoUpdate2Back.getActivityContent() );

        /*活动报名开始时间*/
        activityInfo.setActivitySignstartdate( timeTransfer.String2Timestamp( activityInfoUpdate2Back.getActivitySignstartdate() ) );

        /*活动报名结束时间*/
        activityInfo.setActivitySignenddate( timeTransfer.String2Timestamp( activityInfoUpdate2Back.getActivitySignenddate() ) );

        /*活动开始时间*/
        activityInfo.setActivityStartdate( timeTransfer.String2Timestamp( activityInfoUpdate2Back.getActivityStartdate() ) );

        /*活动结束时间*/
        activityInfo.setActivityEnddate( timeTransfer.String2Timestamp( activityInfoUpdate2Back.getActivityEnddate() ) );

        /*活动费用*/
        activityInfo.setActivityFee( activityInfoUpdate2Back.getActivityFee() );

        /*活动总人数*/
        activityInfo.setActivityPeoplelimit( activityInfoUpdate2Back.getActivityPeoplelimit() );

        /*活动地址*/
        activityInfo.setActivityAddress( activityInfoUpdate2Back.getActivityAddress() );

        /*活动地址名称*/
        activityInfo.setActivityAddressname( activityInfoUpdate2Back.getActivityAddressname() );

        /*活动经度*/
        //log.info( "转换前： " +  activityInfo2Back.getActivityLatitude() );
        //BigDecimal bd = new BigDecimal( activityInfo2Back.getActivityLatitude()  );
        //log.info( "转换后： " + bd );
        activityInfo.setActivityLatitude( new BigDecimal( activityInfoUpdate2Back.getActivityLatitude()  ));

        /*活动纬度*/
        activityInfo.setActivityLongitude( new BigDecimal( activityInfoUpdate2Back.getActivityLongitude() ) );

        /*活动发起人*/
        activityInfo.setActivityOrganizer( activityInfoUpdate2Back.getActivityOrganizer() );

        /*活动发起人手机号*/
        activityInfo.setActivityOrganizerphonenumber( activityInfoUpdate2Back.getActivityOrganizerphonenumber() );

        /*活动发起人openid*/
        activityInfo.setActivityOrganizerid( Wxresult.getOpenid() );

        activityInfo.setActivityImageurl1( activityInfoUpdate2Back.getFileurl1() );

        activityInfo.setActivityImageurl2( activityInfoUpdate2Back.getFileurl2() );

        activityInfo.setActivityImageurl3( activityInfoUpdate2Back.getFileurl3() );

        save( activityInfo );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_UPDATE_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_UPDATE_SUCCESS.getMessage(), null );
    }

    @Override
    @Transactional
    public void delete(Integer activityId) {

        ActivityInfo activityInfo = activityInfoRepository.getOne( activityId );
        activityInfoRepository.delete( activityInfo );
    }

    @Override
    @Transactional
    public ResultVO ActivityDelete(String userKey, Integer activityId) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        ActivityInfo activityInfo = activityInfoRepository.getOne( activityId );

        activityInfo.setActivityValid( ActivityInfoStatusEnum.ACTIVITY_STATUS_DELETE.getCode() );

        save( activityInfo );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_DELETE_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_DELETE_SUCCESS.getMessage(), null );
    }

    @Override
    @Transactional
    public ResultVO GetActivityNearby(MapInfo2Back mapinfo2Back) {

        log.info("[地图信息]              [{}]", mapinfo2Back.toString() );

        String userKey = mapinfo2Back.getUserIdMd5();
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        BigDecimal UserLongitude = mapinfo2Back.getActivityLongitude();
        BigDecimal UserLatitude = mapinfo2Back.getActivityLatitude();

        activityListCheck.ActivitylistCheck();

        List< ActivityInfo > activityInfoList = findall();

        List< ActivityInfo > activityInfoValidList = new ArrayList<>();

        for( ActivityInfo key : activityInfoList )
        {
            double dis = calculateDistance.getDistance( UserLatitude.doubleValue(), UserLongitude.doubleValue(), key.getActivityLatitude().doubleValue(), key.getActivityLongitude().doubleValue() );
            if( dis <= R && key.getActivityValid().equals( ActivityInfoStatusEnum.ACTIVITY_STATUS_VALID.getCode() ) )
            {
                activityInfoValidList.add( key );
            }
        }

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_NEARBY_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_NEARBY_SUCCESS.getMessage(), activityInfoValidList );
    }

    @Override
    @Transactional
    public ResultVO GetPeopleRegisteredList(String userKey, Integer activityId) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        List<UserInfoToFront> userInfoToFrontList = new ArrayList<>();

        List<ActivityMember> activityMemberList = activityMemberList = activityMemberService.findallByActivityId( activityId );

        for( ActivityMember key: activityMemberList )
        {
            String userId = key.getActivityUserid();
            UserInfo userInfo = userInfoService.FindOneById( userId );
            userInfoToFrontList.add( userInfo2UserInfoToFront.UserInfoTransfer( userInfo ) );
        }
        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_PEOPLEREGISTERED_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_PEOPLEREGISTERED_SUCCESS.getMessage(), userInfoToFrontList );
    }


    @Override
    @Transactional
    public List<ActivityInfo> SearchActivity(String SearchStr) {
        return activityInfoRepository.findAllByActivitySubjectContaining( SearchStr );
    }

    @Override
    @Transactional
    public ResultVO GetSearchActivity(String SearchStr) {

        List< ActivityInfo > activityInfoList = SearchActivity( SearchStr );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_FindBYSTR_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_FindBYSTR_SUCCESS.getMessage(), activityInfoList );

    }

    @Override
    @Transactional
    public ResultVO BackActivityDelete(Integer activityId) {

        /*删除用户收藏的该活动*/
        List<ActivityCollect>  activityCollectList = activityCollectService.FindallByActivityId( activityId );

        for( ActivityCollect key: activityCollectList )
        {
            activityCollectService.delete( key );
        }

        /*删除用户参加的该活动*/

        List< ActivityMember > activityMemberList = activityMemberService.findallByActivityId( activityId );

        for( ActivityMember key: activityMemberList )
        {
            activityMemberService.delete( key );
        }

        /*删除用户参加的该活动的报名信息*/

        List< UserparticipationInfo > userparticipationInfoList = userparticipationInfoService.findAllByUserparticipationActivityid( activityId );

        for( UserparticipationInfo key: userparticipationInfoList )
        {
            userparticipationInfoService.delete( key );
        }

        /*删除该活动*/

        delete( activityId );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_DELETE.getCode(), ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_DELETE.getMessage(), null );
    }

    @Override
    @Transactional
    public ResultVO BackActivityList() {

        List< ActivityInfo > activityInfoList = findall();
        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_List.getCode(), ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_List.getMessage(), activityInfoList );
    }

    @Override
    @Transactional
    public ResultVO BackSearchActivity(String SearchStr) {

        List< ActivityInfo > activityInfoList = SearchActivity( SearchStr );
        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_SEARCH.getCode(), ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_SEARCH.getMessage(), activityInfoList );
    }

    @Override
    @Transactional
    public ResultVO BackGetOneActivity(Integer activityId) {

        ActivityInfo activityInfo = FindOnebyId( activityId );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_GETONE.getCode(), ActivityInfoStatusEnum.ACTIVITY_BACK_STATUS_GETONE.getMessage(),activityInfo );
    }
}
