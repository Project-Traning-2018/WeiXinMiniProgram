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

    /*查看已发布活动**/
    List<ActivityInfo> FindAllbyId( String userId );
}
