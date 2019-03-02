package com.activityhelper.BackController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.ActivityInfoServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/19 9:08 AM
 */
@RestController
@RequestMapping( "/back" )
public class BackActivityInfoController {

    @Autowired
    ActivityInfoServiceimpl activityInfoService;

    /*后端删除活动*/
    @PostMapping( "/activityinfo/delete" )
    public ResultVO BackActivityDelete( @RequestParam("activityId") Integer activityId )
    {
        return activityInfoService.BackActivityDelete( activityId );
    }

    /*后端获取活动列表*/
    @PostMapping( "/activityinfo/list" )
    public ResultVO BackActivityList( )
    {
        return activityInfoService.BackActivityList();
    }

    /*后端搜索活动*/
    @PostMapping( "/activityinfo/search" )
    public ResultVO BackSearchActivity( @RequestParam("SearchStr") String SearchStr )
    {
        return activityInfoService.BackSearchActivity( SearchStr );
    }

    /*后端查看一条活动*/
    @PostMapping( "/activityinfo/getone" )
    public ResultVO BackGetOneActivity( @RequestParam("activityId") Integer activityId )
    {
        return activityInfoService.BackGetOneActivity( activityId );
    }
}
