package com.activityhelper.Conversion;

import com.activityhelper.Dao.UserInfo;
import com.activityhelper.Pojo.UserInfoToFront;
import org.springframework.stereotype.Component;

/**
 * @author byene
 * @date 2019/2/16 10:00 PM
 */
@Component
public class UserInfo2UserInfoToFront {

    public UserInfoToFront UserInfoTransfer(UserInfo userInfo )
    {
        UserInfoToFront userInfoToFront = new UserInfoToFront();

        userInfoToFront.setUserName( userInfo.getUserName() );
        userInfoToFront.setUserIcon( userInfo.getUserIcon() );
        userInfoToFront.setUserPhonenumber( userInfo.getUserPhonenumber() );
        return userInfoToFront;
    }

}
