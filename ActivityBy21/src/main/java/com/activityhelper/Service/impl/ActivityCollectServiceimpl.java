package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Dao.ActivityCollect;
import com.activityhelper.Dao.ActivityInfo;
import com.activityhelper.Enums.ActivityInfoStatusEnum;
import com.activityhelper.Enums.WxInfoStausEnum;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.WxInfo;
import com.activityhelper.Repository.ActivityCollectRepository;
import com.activityhelper.Service.ActivityCollectService;
import com.activityhelper.Service.ActivityInfoService;
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
 * @date 2019/2/17 8:02 PM
 */
@Service
@Slf4j
public class ActivityCollectServiceimpl implements ActivityCollectService {

    @Autowired
    ActivityCollectRepository activityCollectRepository;

    @Autowired
    ActivityInfoServiceimpl  activityInfoService;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Override
    @Transactional
    public void save(ActivityCollect activityCollect) {
        activityCollectRepository.save( activityCollect );
    }

    @Override
    @Transactional
    public List<ActivityCollect> FindallByUserId(String userId) {
        return activityCollectRepository.findAllByUsercollectUseridIn( userId );
    }

    @Override
    @Transactional
    public List<ActivityCollect> FindallByActivityId(Integer activityId) {
        return activityCollectRepository.findAllByUsercollectActivityidIn( activityId );
    }

    @Override
    @Transactional
    public void delete(ActivityCollect activityCollect) {
        activityCollectRepository.delete( activityCollect );
    }

    @Override
    @Transactional
    public ResultVO ActivityCollectSave(String userKey, Integer activityId) {

        log.info("[userKey]              [{}]", userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );
        String userId = Wxresult.getOpenid();

        /*无法重复收藏活动*/
        List<ActivityCollect> activityCollectList = new ArrayList<>();

        boolean flag = false;

        for( ActivityCollect key: activityCollectList )
        {
            if( key.getUsercollectActivityid().equals( activityId ) )
            {
                flag = true;
                break;
            }
        }

        if( flag )
            return getResultVOInfo.GetResultVO(ActivityInfoStatusEnum.ACTIVITY_COLLECT_ERROR.getCode(), ActivityInfoStatusEnum.ACTIVITY_COLLECT_ERROR.getMessage(), null );

        /*活动收藏成功*/
        ActivityCollect activityCollect = new ActivityCollect();
        activityCollect.setUsercollectUserid( userId );
        activityCollect.setUsercollectActivityid( activityId );

        save( activityCollect );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_COLLECT_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_COLLECT_SUCCESS.getMessage(), null );

    }

    @Override
    @Transactional
    public ResultVO ActivityCollectList(String userKey) {

        log.info( "userKey值:  " + userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );
        String userId = Wxresult.getOpenid();

        List< ActivityCollect > activityCollectList = FindallByUserId( userId );

        List<ActivityInfo> activityInfoList = new ArrayList<>();

        for( ActivityCollect key: activityCollectList )
        {
            Integer activitykey = key.getUsercollectActivityid();
            activityInfoList.add( activityInfoService.FindOnebyId( activitykey ) );
        }

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_COLLECT_LIST_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_COLLECT_LIST_SUCCESS.getMessage(), activityInfoList );
    }

    @Override
    @Transactional
    public ResultVO ActivityCollectDelete(String userKey, Integer activityId) {

        log.info( "userKey值:  " + userKey );
        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );
        String userId = Wxresult.getOpenid();

        List< ActivityCollect > activityCollectList = FindallByUserId( userId );

        ActivityCollect activityCollectdelete = new ActivityCollect();

        for( ActivityCollect key : activityCollectList )
        {
            if( key.getUsercollectActivityid().equals( activityId ) )
            {
                activityCollectdelete = key;
                break;
            }
        }

        delete( activityCollectdelete );

        return getResultVOInfo.GetResultVO( ActivityInfoStatusEnum.ACTIVITY_COLLECT_DELETE_SUCCESS.getCode(), ActivityInfoStatusEnum.ACTIVITY_COLLECT_DELETE_SUCCESS.getMessage(), null );

    }
}
