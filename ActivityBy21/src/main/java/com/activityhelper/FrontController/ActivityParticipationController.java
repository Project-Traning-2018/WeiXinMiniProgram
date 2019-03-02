package com.activityhelper.FrontController;

import com.activityhelper.Pojo.ActivityChooseInfo2Back;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.ActivityParticipateServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author byene
 * @date 2019/2/18 11:33 AM
 */
@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityParticipationController {

    @Autowired
    ActivityParticipateServiceimpl  activityParticipateService;

    /*参加活动*/
    @PostMapping( "/activityinfo/participate" )
    public ResultVO ActivityParticipationInfo(@RequestBody ActivityChooseInfo2Back activityChooseInfo2Back )
    {
        return activityParticipateService.ActivityParticipationInfo( activityChooseInfo2Back );
    }

    /*取消参加活动*/
    @PostMapping( "/activityinfo/participatecancel" )
    public ResultVO ActivityInfopParticipateCancel(@RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId  )
    {
        return activityParticipateService.ActivityInfopParticipateCancel( userKey, activityId );
    }

    /*获取参加活动列表*/
    @PostMapping( "/activityinfo/participatelist" )
    public ResultVO ActivityInfoParticipateList( @RequestParam("userKey") String userKey )
    {
        return activityParticipateService.ActivityInfoParticipateList( userKey );
    }

    /*更新参加活动信息*/
    @PostMapping( "/activityinfo/participateupdate" )
    public ResultVO ActivityInfoParticipationUpdate(@RequestBody ActivityChooseInfo2Back activityChooseInfo2Back)
    {
        return activityParticipateService.ActivityInfoParticipationUpdate( activityChooseInfo2Back );
    }

}
