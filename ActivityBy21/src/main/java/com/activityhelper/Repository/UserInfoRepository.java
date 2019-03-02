package com.activityhelper.Repository;

import com.activityhelper.Dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/11 4:33 AM
 */
public interface UserInfoRepository extends JpaRepository< UserInfo, String > {

    List< UserInfo > findAllByUserNameContaining( String str );
}
