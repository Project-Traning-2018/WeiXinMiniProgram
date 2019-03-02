package com.activityhelper.Check;

import com.activityhelper.Dao.ActivityInfo;
import com.activityhelper.Enums.ActivityInfoStatusEnum;
import com.activityhelper.Service.impl.ActivityInfoServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/18 11:29 AM
 */
@Component
@Slf4j
public class ActivityCheck {

    @Autowired
    ActivityInfoServiceimpl activityInfoService;

    @Scheduled( fixedRate = 3600000 )
    public void ActivityUpdate()
    {
        List<ActivityInfo> activityInfoList = activityInfoService.findall();
        Integer cnt = 0;

        /*获取系统当前时间*/
        Timestamp CurrentTime = new Timestamp( System.currentTimeMillis() );

        for( ActivityInfo key: activityInfoList )
        {
            if(  CurrentTime.after( key.getActivitySignenddate() ) )
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
