package com.byene.Controller;

import com.byene.Conversion.TimeTransfer;
import com.byene.Dao.ActivityInfo;
import com.byene.Enums.ActivityInfoStatusEnum;
import com.byene.Enums.WxInfoStausEnum;
import com.byene.Pojo.ActivityInfo2Back;
import com.byene.Pojo.ResultVO;
import com.byene.Pojo.WxInfo;
import com.byene.Service.impl.ActivityInfoServiceImpl;
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
    StringRedisTemplate strRedis;

    @Autowired
    TimeTransfer timeTransfer;


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

}
