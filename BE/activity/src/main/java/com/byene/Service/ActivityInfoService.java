package com.byene.Service;

import com.byene.Dao.ActivityInfo;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/11 10:57 AM
 */
public interface ActivityInfoService {

    /*保存活动信息**/
    void save( ActivityInfo activityInfo );

    /*查看全部活动**/
    List<ActivityInfo> findall( );

    /*根据活动删除活动**/
    void delete( Integer activityId );

    /*查看用户已发布活动**/
    List<ActivityInfo> FindAllbyId( String userId );

    /*根据活动Id查询活动*/
    ActivityInfo FindOnebyId( Integer activityId );
}
