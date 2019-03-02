package com.activityhelper.Dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author byene
 * @date 2019/2/10 5:31 AM
 */
@Entity
@Table( name = "user_info")
@Data
@JsonIgnoreProperties( {"hibernateLazyInitializer","handler"} )
public class UserInfo {

    @Id
    @NotFound( action = NotFoundAction.IGNORE)
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
