package com.byene.Pojo;

import lombok.Data;

/**
 * 用户信息和登录状态的实体类
 * @author byene
 * @date 2019/1/8 9:14 PM
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
