package com.activityhelper.Service;

import com.activityhelper.Pojo.ActivityChooseInfo2Back;
import com.activityhelper.Pojo.ActivityParticipationInfo2Front;
import com.activityhelper.Pojo.ResultVO;

/**
 * @author byene
 * @date 2019/2/17 9:06 PM
 */
public interface ActivityParticipateService {

    /*获取活动报名信息*/
    ResultVO GetActivityInfoParticipateInfo( String userKey, Integer activityId );

    /*参加活动*/
    ResultVO ActivityParticipationInfo( ActivityChooseInfo2Back activityChooseInfo2Back );

    /*取消参加活动*/
    ResultVO ActivityInfopParticipateCancel( String userKey, Integer activityId  );

    /*获取参加活动列表*/
    ResultVO ActivityInfoParticipateList( String userKey );

    /*更新参加活动信息*/
    ResultVO ActivityInfoParticipationUpdate( ActivityChooseInfo2Back activityChooseInfo2Back );
}
