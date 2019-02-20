package com.activityhelper.FrontController;

import com.activityhelper.Pojo.ActivityInfo2Back;
import com.activityhelper.Pojo.ActivityInfoUpdate2Back;
import com.activityhelper.Pojo.MapInfo2Back;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.ActivityInfoServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author byene
 * @date 2019/2/15 9:22 PM
 */

@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityInfoController {


    @Autowired
    ActivityInfoServiceimpl activityInfoService;

    /*保存活动信息*/
    @PostMapping( "/activityinfo/save" )
    public ResultVO ActivityInfoSave( @RequestBody ActivityInfo2Back activityInfo2Back )
    {
        return activityInfoService.ActivityInfoSave( activityInfo2Back );
    }

    /*查询已发布活动列表*/
    @PostMapping( "/activityinfo/listbyid" )
    public ResultVO ActivityInfoListByid(  @RequestParam("userKey") String userKey )
    {
        return activityInfoService.ActivityInfoListById( userKey );
    }

    /*更新活动信息*/
    @PostMapping( "/activityinfo/update" )
    public ResultVO ActivityInfoUpdate(  @RequestBody ActivityInfoUpdate2Back activityInfoUpdate2Back )
    {
        return activityInfoService.ActivityInfoUpdate( activityInfoUpdate2Back );
    }

    /*删除已发布活动*/
    @PostMapping( "/activityinfo/deletebyorganizer" )
    public ResultVO ActivityInfoDeletebyOrganizer( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId )
    {
        return activityInfoService.ActivityDelete( userKey, activityId );
    }

    /*查询附近已发布活动*/
    @PostMapping( "/activityinfo/getactivitynearby" )
    public ResultVO GetActivityNearby( @RequestBody MapInfo2Back mapinfo2Back )
    {
        return activityInfoService.GetActivityNearby( mapinfo2Back );
    }

    /*查询活动已报名人数列表*/
    @PostMapping( "/activityinfo/peopleregisteredlist" )
    public ResultVO GetPeopleRegisteredList( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId )
    {
        return activityInfoService.GetPeopleRegisteredList( userKey, activityId );
    }

    /*搜寻活动*/
    @PostMapping( "/activityinfo/searchactivity" )
    public ResultVO GetSearchActivity( @RequestParam("SearchStr") String SearchStr )
    {
        return activityInfoService.GetSearchActivity( SearchStr );
    }
}
