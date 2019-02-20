package com.activityhelper.Repository;

import com.activityhelper.Dao.ActivityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/10 5:12 AM
 */
public interface ActivityInfoRepository extends JpaRepository<ActivityInfo, Integer> {


    //根据发起人Id查询活动
    List< ActivityInfo > findAllByActivityOrganizeridIn( String organizerId );

    //根据字符串来匹配已查询活动
    List< ActivityInfo > findAllByActivitySubjectContaining( String SearchStr );
}
