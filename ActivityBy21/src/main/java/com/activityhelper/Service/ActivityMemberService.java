package com.activityhelper.Service;

import com.activityhelper.Dao.ActivityMember;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/17 8:52 PM
 */
public interface ActivityMemberService {

    /*保存*/
    void save( ActivityMember activityMember );

    /*删除*/
    void delete( ActivityMember activityMember );

    /*根据用户ID删除一个*/
    void deleteOneByUserId(String userId, Integer ActivityId);

    /*根据活动ID查询全部*/
    List< ActivityMember > findallByActivityId(Integer activityId );

    /*根据用户ID查询全部*/
    List< ActivityMember > findallByUserId( String userId );

}
