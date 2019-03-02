package com.activityhelper.Service;

import com.activityhelper.Dao.ActivityInfo;
import com.activityhelper.Pojo.ActivityInfo2Back;
import com.activityhelper.Pojo.ActivityInfoUpdate2Back;
import com.activityhelper.Pojo.MapInfo2Back;
import com.activityhelper.Pojo.ResultVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/15 7:58 PM
 */
public interface ActivityInfoService {

    /*保存活动信息**/
    void save( ActivityInfo activityInfo );

    ResultVO ActivityInfoSave(ActivityInfo2Back activityInfo2Back );

    /*查看全部活动**/
    List<ActivityInfo> findall( );

    /*根据活动ID删除活动**/
    void delete( Integer activityId );

    /*前端删除活动*/
    ResultVO ActivityDelete( String userKey, Integer activityId );

    /*查看用户已发布活动**/
    List<ActivityInfo> FindAllbyId( String userId );

    ResultVO ActivityInfoListById( String userKey );

    /*根据活动Id查询活动*/
    ActivityInfo FindOnebyId( Integer activityId );

    /*更新活动信息*/
    ResultVO ActivityInfoUpdate( ActivityInfoUpdate2Back activityInfoUpdate2Back );

    /*查看附近已发布活动*/
    ResultVO GetActivityNearby( MapInfo2Back mapinfo2Back );

    /*查询活动已报名人数列表*/
    ResultVO GetPeopleRegisteredList( String userKey, Integer activityId );

    /*搜索活动*/
    List< ActivityInfo > SearchActivity( String SearchStr );

    ResultVO GetSearchActivity( String SearchStr );

    /*后端删除活动*/
    ResultVO BackActivityDelete( Integer activityId );

    /*后端查看全部活动列表*/
    ResultVO BackActivityList( );

    /*后端搜索活动*/
    ResultVO BackSearchActivity( String SearchStr );

    /*后端查看一条活动*/
    ResultVO BackGetOneActivity( Integer activityId );
}
