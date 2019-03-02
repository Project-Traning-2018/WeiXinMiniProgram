package com.activityhelper.Repository;

import com.activityhelper.Dao.ActivityMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/17 8:56 PM
 */
public interface ActivityMemberRepository extends JpaRepository<ActivityMember, Integer> {

    List< ActivityMember > findAllByActivityUseridIn(String userId );

    List< ActivityMember > findAllByActivityActivityidIn( Integer activityId );

}
