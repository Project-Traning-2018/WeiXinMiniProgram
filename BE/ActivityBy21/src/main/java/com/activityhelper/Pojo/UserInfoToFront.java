package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/16 9:49 PM
 */
@Data
public class UserInfoToFront {

    private  String userName;

    private  String userIcon;

    private  String userPhonenumber;

    public UserInfoToFront() {
    }

    public UserInfoToFront(String userName, String userIcon, String userPhonenumber) {
        this.userName = userName;
        this.userIcon = userIcon;
        this.userPhonenumber = userPhonenumber;
    }

}
