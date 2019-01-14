package com.byene.Check;

import com.byene.Dao.ActivityInfo;
import com.byene.Enums.ActivityInfoStatusEnum;
import com.byene.Service.impl.ActivityInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * 定时检查活动有效性
 * @author byene
 * @date 2019/1/13 2:34 PM
 */
@Component
@Slf4j
public class ActivityCheck {

    @Autowired
    ActivityInfoServiceImpl activityInfoService;

    @Scheduled( fixedRate = 3600000 )
    public void ActivityUpdate()
    {
        List< ActivityInfo > activityInfoList = activityInfoService.findall();
        Integer cnt = 0;

        /*获取系统当前时间*/
        Timestamp CurrentTime = new Timestamp( System.currentTimeMillis() );

        for( ActivityInfo key: activityInfoList )
        {
            if( CurrentTime.before( key.getActivitySignenddate() ) )
            {
                key.setActivityValid( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode() );
                activityInfoService.save( key );
                cnt ++;
            }
        }
        log.info( "开始进行活动状态更新" );
        log.info( "已更新活动数量： " + cnt.toString() );
    }
}
