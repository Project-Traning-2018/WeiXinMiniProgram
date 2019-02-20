package com.activityhelper.Dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author byene
 * @date 2019/2/10 5:19 AM
 */
@Entity
@Table( name = "userparticipation_info" )
@Data
@JsonIgnoreProperties( {"hibernateLazyInitializer","handler"} )
public class UserparticipationInfo {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer userparticipationId;

    private Integer userparticipationActivityid;

    private String  userparticipationUserid;

    private String  userparticipationItem1;

    private String  userparticipationItem2;

    private String  userparticipationItem3;

    private String  userparticipationItem4;

    private String  userparticipationItem5;

    private String  userparticipationItem6;

    private String  userparticipationItem7;

    private String  userparticipationItem8;

    private String  userparticipationItem9;

    private String  userparticipationItem10;

    public UserparticipationInfo() {
    }

    public UserparticipationInfo(Integer userparticipationActivityid, String userparticipationUserid, String userparticipationItem1, String userparticipationItem2, String userparticipationItem3, String userparticipationItem4, String userparticipationItem5, String userparticipationItem6, String userparticipationItem7, String userparticipationItem8, String userparticipationItem9, String userparticipationItem10) {
        this.userparticipationActivityid = userparticipationActivityid;
        this.userparticipationUserid = userparticipationUserid;
        this.userparticipationItem1 = userparticipationItem1;
        this.userparticipationItem2 = userparticipationItem2;
        this.userparticipationItem3 = userparticipationItem3;
        this.userparticipationItem4 = userparticipationItem4;
        this.userparticipationItem5 = userparticipationItem5;
        this.userparticipationItem6 = userparticipationItem6;
        this.userparticipationItem7 = userparticipationItem7;
        this.userparticipationItem8 = userparticipationItem8;
        this.userparticipationItem9 = userparticipationItem9;
        this.userparticipationItem10 = userparticipationItem10;
    }
}
