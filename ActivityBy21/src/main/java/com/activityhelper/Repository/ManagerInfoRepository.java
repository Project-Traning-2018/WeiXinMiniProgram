package com.activityhelper.Repository;

import com.activityhelper.Dao.ManagerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/13 7:52 PM
 */
public interface ManagerInfoRepository extends JpaRepository< ManagerInfo, Integer > {

    ManagerInfo findByManagerLoginnoIn( String managerLoginno );

    //根据字符串来匹配查询管理员
    List< ManagerInfo > findAllByManagerLoginnoContaining( String str );
}
