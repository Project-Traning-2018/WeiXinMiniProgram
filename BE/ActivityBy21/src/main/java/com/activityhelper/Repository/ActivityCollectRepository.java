package com.activityhelper.Repository;

import com.activityhelper.Dao.ActivityCollect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/17 7:58 PM
 */
public interface ActivityCollectRepository extends JpaRepository<ActivityCollect, Integer > {

    List< ActivityCollect > findAllByUsercollectUseridIn(String userId );

    List< ActivityCollect > findAllByUsercollectActivityidIn( Integer activityId );

}
