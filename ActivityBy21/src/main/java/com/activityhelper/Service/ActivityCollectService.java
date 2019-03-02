package com.activityhelper.Service;

import com.activityhelper.Dao.ActivityCollect;
import com.activityhelper.Pojo.ResultVO;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/17 7:59 PM
 */
public interface ActivityCollectService {

    /*保存收藏活动信息*/
    void save( ActivityCollect activityCollect );

    /*根据用户Id查看收藏活动列表*/
    List< ActivityCollect > FindallByUserId(String userId );

    /*根据活动Id查看收藏活动列表*/
    List< ActivityCollect > FindallByActivityId( Integer activityId );

    /*删除已收藏活动*/
    void delete( ActivityCollect activityCollect );

    /*保存收藏活动*/
    ResultVO ActivityCollectSave( String userKey, Integer activityId );

    /*收藏活动列表*/
    ResultVO ActivityCollectList( String userKey );

    /*删除收藏活动*/
    ResultVO ActivityCollectDelete( String userKey, Integer activityId );

}
