package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/10 6:50 AM
 */

@Data
public class UserInfo2Back {

    /*前端用户登录凭证对应值*/
    private  String userIdMd5;

    private  String userName;

    private  String userIcon;

    private  String userPhonenumber;

    public UserInfo2Back() {
    }

    public UserInfo2Back(String userIdMd5, String userName, String userIcon, String userPhonenumber) {
        this.userIdMd5 = userIdMd5;
        this.userName = userName;
        this.userIcon = userIcon;
        this.userPhonenumber = userPhonenumber;
    }

}
