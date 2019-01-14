package com.byene.Controller;

import com.byene.Dao.ActivityCollect;
import com.byene.Enums.ActivityInfoStatusEnum;
import com.byene.Enums.WxInfoStausEnum;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.ActivityCollectServiceImpl;
import com.byene.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动收藏相关操作
 * @author byene
 * @date 2019/1/13 9:35 PM
 */

@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityCollectController {

    @Autowired
    ActivityCollectServiceImpl activityCollectService;

    @Autowired
    StringRedisTemplate strRedis;


    /*保存收藏活动*/
    @PostMapping( "/activitycollect/save" )
    public ResultVO ActivityCollectSave( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId)
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
        {
            resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_COLLECT_ERROR.getCode() );
            resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_COLLECT_ERROR.getMessage() );
            return resultVO;
        }

        /*活动收藏成功*/
        ActivityCollect activityCollect = new ActivityCollect();
        activityCollect.setUsercollectUserid( userId );
        activityCollect.setUsercollectActivityid( activityId );

        activityCollectService.save( activityCollect );

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_COLLECT_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_COLLECT_SUCCESS.getMessage() );
        return resultVO;
    }

    /*收藏活动列表*/
    @PostMapping( "/activitycollect/list" )
    public ResultVO ActivityCollectList( @RequestParam("userKey") String userKey )
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

        List< ActivityCollect > activityCollectList = activityCollectService.FindallByUserId( userId );

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_COLLECT_LIST_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_COLLECT_LIST_SUCCESS.getMessage() );
        resultVO.setData( activityCollectList );
        return resultVO;
    }

    /*删除收藏活动*/
    @PostMapping( "/activitycollect/delete" )
    public ResultVO ActivityCollectDelete( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId )
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

        List< ActivityCollect > activityCollectList = activityCollectService.FindallByUserId( userId );

        ActivityCollect activityCollectdelete = new ActivityCollect();

        for( ActivityCollect key : activityCollectList )
        {
            if( key.getUsercollectActivityid().equals( activityId ) )
            {
                activityCollectdelete = key;
                break;
            }
        }

        activityCollectService.delete( activityCollectdelete );

        resultVO.setCode( ActivityInfoStatusEnum.ACTIVITY_COLLECT_DELETE_SUCCESS.getCode() );
        resultVO.setMsg( ActivityInfoStatusEnum.ACTIVITY_COLLECT_DELETE_SUCCESS.getMessage() );
        return resultVO;
    }
}
