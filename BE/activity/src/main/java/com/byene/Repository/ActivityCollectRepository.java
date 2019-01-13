package com.byene.Repository;

import com.byene.Dao.ActivityCollect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/13 9:22 PM
 */
public interface ActivityCollectRepository extends JpaRepository< ActivityCollect, Integer > {

    List< ActivityCollect > findAllByUsercollectUseridIn( String userId );

    List< ActivityCollect > findAllByUsercollectActivityidIn( Integer activityId );

}
