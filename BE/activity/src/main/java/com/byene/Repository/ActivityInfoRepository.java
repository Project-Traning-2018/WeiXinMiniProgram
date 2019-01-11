package com.byene.Repository;

import com.byene.Dao.ActivityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/11 9:22 AM
 */
public interface ActivityInfoRepository extends JpaRepository<ActivityInfo, Integer> {

    List< ActivityInfo > findAllByActivityOrganizeridIn( String organizerId );
}
