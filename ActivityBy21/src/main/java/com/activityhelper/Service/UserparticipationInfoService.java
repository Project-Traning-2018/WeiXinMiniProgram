package com.activityhelper.Service;

import com.activityhelper.Dao.UserparticipationInfo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/18 8:38 AM
 */
public interface UserparticipationInfoService {

    /*保存用户报名活动信息*/
    void save( UserparticipationInfo userparticipationInfo );

    /*删除用户报名活动信息*/
    void delete( UserparticipationInfo userparticipationInfo );

    /*查询一条用户报名活动信息*/
    UserparticipationInfo findone( Integer Activityid, String Userid  );

    /*根据活动Id查询全部用户参加活动信息*/
    List< UserparticipationInfo > findAllByUserparticipationActivityid( Integer Activityid );

}
