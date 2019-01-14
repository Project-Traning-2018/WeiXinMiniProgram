package com.byene.Conversion;

import com.byene.Dao.UserInfo;
import com.byene.Pojo.UserInfoToFront;
import org.springframework.stereotype.Component;

/**
 * @author byene
 * @date 2019/1/14 9:18 AM
 */
@Component
public class UserInfo2UserInfoToFront {

    public UserInfoToFront UserInfoTransfer( UserInfo userInfo )
    {
        UserInfoToFront userInfoToFront = new UserInfoToFront();

        userInfoToFront.setUserName( userInfo.getUserName() );
        userInfoToFront.setUserIcon( userInfo.getUserIcon() );
        userInfoToFront.setUserPhonenumber( userInfo.getUserPhonenumber() );
        return userInfoToFront;
    }
}
