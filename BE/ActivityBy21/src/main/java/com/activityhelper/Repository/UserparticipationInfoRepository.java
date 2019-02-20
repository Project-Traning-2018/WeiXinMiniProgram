package com.activityhelper.Repository;

import com.activityhelper.Dao.UserparticipationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/18 8:37 AM
 */
public interface UserparticipationInfoRepository  extends JpaRepository<UserparticipationInfo, Integer > {


    List< UserparticipationInfo >  findAllByUserparticipationActivityid( Integer  Activityid );


}
