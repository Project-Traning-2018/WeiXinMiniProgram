package com.byene.Dao;

import lombok.Data;

import javax.persistence.*;

/**
 * @author byene
 * @date 2019/1/13 9:19 PM
 */

@Entity
@Table( name = "activity_collect" )
@Data
public class ActivityCollect {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private  Integer usercollectId;

    private  String  usercollectUserid;

    private  Integer usercollectActivityid;

    public ActivityCollect() {
    }

    public ActivityCollect(String usercollectUserid, Integer usercollectActivityid) {
        this.usercollectUserid = usercollectUserid;
        this.usercollectActivityid = usercollectActivityid;
    }
}
