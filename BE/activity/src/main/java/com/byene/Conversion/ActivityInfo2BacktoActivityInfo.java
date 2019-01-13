package com.byene.Conversion;

import com.byene.Dao.ActivityInfo;
import com.byene.Pojo.Activity.ActivityInfo2Back;
import com.byene.Pojo.WxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author byene
 * @date 2019/1/13 2:10 PM
 */
@Component
public class ActivityInfo2BacktoActivityInfo {

    @Autowired
    TimeTransfer timeTransfer;

    public ActivityInfo Transfer(WxInfo Wxresult, ActivityInfo2Back activityInfo2Back )
    {
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

        return activityInfo;
    }
}
