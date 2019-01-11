package com.byene.Service;

import com.byene.Dao.ManagerInfo;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/11 5:13 PM
 */
public interface ManagerInfoService {

    void save( ManagerInfo managerInfo );

    void delete( String managerLoginno );

    void update( ManagerInfo managerInfo );

    List< ManagerInfo > findall( String managerLoginno );

    ManagerInfo findbymanagerId( String managerLoginno );
}
