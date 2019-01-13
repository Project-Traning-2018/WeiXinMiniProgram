package com.byene.Repository;

import com.byene.Dao.ManagerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author byene
 * @date 2019/1/11 5:11 PM
 */
public interface ManagerInfoRepository extends JpaRepository<ManagerInfo, Integer> {

    ManagerInfo findByManagerLoginnoIn( String managerLoginno );

}
