package com.activityhelper.FrontController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.ActivityCollectServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/17 8:35 PM
 */

@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityCollectController {

    @Autowired
    ActivityCollectServiceimpl activityCollectService;

    /*保存收藏活动*/
    @PostMapping( "/activitycollect/save" )
    public ResultVO ActivityCollectSave(@RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId)
    {
        return activityCollectService.ActivityCollectSave( userKey, activityId );
    }

    /*收藏活动列表*/
    @PostMapping( "/activitycollect/list" )
    public ResultVO ActivityCollectList( @RequestParam("userKey") String userKey )
    {
        return activityCollectService.ActivityCollectList( userKey );
    }

    /*删除收藏活动*/
    @PostMapping( "/activitycollect/delete" )
    public ResultVO ActivityCollectDelete( @RequestParam("userKey") String userKey, @RequestParam("activityId") Integer activityId )
    {
        return activityCollectService.ActivityCollectDelete( userKey, activityId );
    }

}
