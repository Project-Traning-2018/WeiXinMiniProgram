package com.byene.Dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author byene
 * @date 2019/1/8 7:39 PM
 */
@Entity
@Table( name = "user_info")
@Data
@JsonIgnoreProperties( {"hibernateLazyInitializer","handler"} )
public class UserInfo implements Serializable {

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
