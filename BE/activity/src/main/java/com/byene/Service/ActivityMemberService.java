package com.byene.Service;

import com.byene.Dao.ActivityMember;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/12 3:02 PM
 */
public interface ActivityMemberService {

    void save( ActivityMember activityMember );

    void delete( ActivityMember activityMember );

    void deleteOneByUserId(String userId, Integer ActivityId);

    List< ActivityMember > findallByActivityId(Integer activityId );

    List< ActivityMember > findallByUserId( String userId );
}
