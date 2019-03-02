package com.activityhelper.Check;

import com.activityhelper.Dao.ActivityInfo;
import com.activityhelper.Enums.ActivityInfoStatusEnum;
import com.activityhelper.Service.impl.ActivityInfoServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/20 2:12 PM
 */

@Component
@Slf4j
public class ActivityListCheck {

    @Autowired
    ActivityInfoServiceimpl activityInfoService;

    public void ActivitylistCheck()
    {
        List<ActivityInfo> activityInfoList = activityInfoService.findall();

        /*获取系统当前时间*/
        Timestamp CurrentTime = new Timestamp( System.currentTimeMillis() );

        for( ActivityInfo key: activityInfoList )
        {
            if( CurrentTime.after( key.getActivitySignenddate() ) )
            {
                key.setActivityValid( ActivityInfoStatusEnum.ACTIVITY_INVALID.getCode() );
                activityInfoService.save( key );
            }
        }
    }

}
