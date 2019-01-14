package com.byene.Service;

import com.byene.Dao.ActivityCollect;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/13 9:29 PM
 */
public interface ActivityCollectService {

    /*保存收藏活动信息*/
    void save( ActivityCollect activityCollect );

    /*根据用户Id查看收藏活动列表*/
    List< ActivityCollect > FindallByUserId( String userId );

    /*根据活动Id查看收藏活动列表*/
    List< ActivityCollect > FindallByActivityId( Integer activityId );

    /*删除已收藏活动*/
    void delete( ActivityCollect activityCollect );
}
