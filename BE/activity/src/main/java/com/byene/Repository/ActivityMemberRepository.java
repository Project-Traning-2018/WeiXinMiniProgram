package com.byene.Repository;

import com.byene.Dao.ActivityMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/12 3:00 PM
 */
public interface ActivityMemberRepository extends JpaRepository<ActivityMember, Integer> {

    List< ActivityMember > findAllByActivityUseridIn( String userId );

    List< ActivityMember > findAllByActivityActivityidIn( Integer activityId );
}
