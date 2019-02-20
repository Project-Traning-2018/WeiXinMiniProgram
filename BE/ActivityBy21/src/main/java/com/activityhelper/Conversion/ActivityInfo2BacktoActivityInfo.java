package com.activityhelper.Conversion;

import com.activityhelper.Dao.ActivityInfo;
import com.activityhelper.Pojo.ActivityInfo2Back;
import com.activityhelper.Pojo.WxInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author byene
 * @date 2019/2/15 8:23 PM
 */
@Component
@Slf4j
public class ActivityInfo2BacktoActivityInfo {

    @Autowired
    TimeTransfer timeTransfer;

    @Autowired
    GetFileUrl getFileUrl;

    @Autowired
    InfoMd5 infoMd5;

    public ActivityInfo Transfer(WxInfo Wxresult, ActivityInfo2Back activityInfo2Back )
    {
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

        /*活动报名信息项目1*/
        activityInfo.setActivityparticipationItem1( activityInfo2Back.getActivityparticipationItem1() );

        /*活动报名信息项目2*/
        activityInfo.setActivityparticipationItem2( activityInfo2Back.getActivityparticipationItem2() );

        /*活动报名信息项目3*/
        activityInfo.setActivityparticipationItem3( activityInfo2Back.getActivityparticipationItem3() );

        /*活动报名信息项目4*/
        activityInfo.setActivityparticipationItem4( activityInfo2Back.getActivityparticipationItem4() );

        /*活动报名信息项目5*/
        activityInfo.setActivityparticipationItem5( activityInfo2Back.getActivityparticipationItem5() );

        /*活动报名信息项目6*/
        activityInfo.setActivityparticipationItem6( activityInfo2Back.getActivityparticipationItem6() );

        /*活动报名信息项目7*/
        activityInfo.setActivityparticipationItem7( activityInfo2Back.getActivityparticipationItem7() );

        /*活动报名信息项目8*/
        activityInfo.setActivityparticipationItem8( activityInfo2Back.getActivityparticipationItem8() );

        /*活动报名信息项目9*/
        activityInfo.setActivityparticipationItem9( activityInfo2Back.getActivityparticipationItem9() );

        /*活动报名信息项目10*/
        activityInfo.setActivityparticipationItem10( activityInfo2Back.getActivityparticipationItem10() );

        /*活动图片1url*/
        activityInfo.setActivityImageurl1( activityInfo2Back.getFileurl1() );

        /*活动图片2url*/
        activityInfo.setActivityImageurl2( activityInfo2Back.getFileurl2() );

        /*活动图片3url*/
        activityInfo.setActivityImageurl3( activityInfo2Back.getFileurl3() );

        return activityInfo;
    }

}
