package com.byene.Dao;

import lombok.Data;

import javax.persistence.*;

/**
 * @author byene
 * @date 2019/1/12 2:57 PM
 */
@Entity
@Table( name = "activity_member" )
@Data
public class ActivityMember {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer activityJoinid;

    private String activityUserid;

    private Integer activityActivityid;

    public ActivityMember() {
    }

    public ActivityMember(String activityUserid, Integer activityActivityid) {
        this.activityUserid = activityUserid;
        this.activityActivityid = activityActivityid;
    }
}
