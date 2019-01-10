package com.byene.Dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author byene
 * @date 2019/1/8 7:39 PM
 */
@Entity
@Table( name = "user_info")
@Data
public class UserInfo {

    @Id
    private  String userId;

    private  String userName;

    private  String userIcon;

    private  String userPhonenumber;

    public UserInfo() {
    }

    public UserInfo(String userId, String userName, String userIcon, String userPhonenumber) {
        this.userId = userId;
        this.userName = userName;
        this.userIcon = userIcon;
        this.userPhonenumber = userPhonenumber;
    }

}
