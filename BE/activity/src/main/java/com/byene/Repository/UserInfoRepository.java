package com.byene.Repository;

import com.byene.Dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author byene
 * @date 2019/1/8 7:44 PM
 */
public interface UserInfoRepository extends JpaRepository< UserInfo, String > {

}
