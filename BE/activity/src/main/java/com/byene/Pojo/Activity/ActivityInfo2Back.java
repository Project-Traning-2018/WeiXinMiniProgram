package com.byene.Pojo.Activity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author byene
 * @date 2019/1/11 11:29 AM
 */
@Data
public class ActivityInfo2Back {

    /*前端用户登录凭证对应值*/
    private String userIdMd5;

    private Integer activityType;

    private String activitySubject;

    private String activityContent;

    private String activitySignstartdate;

    private String activitySignenddate;

    private String activityStartdate;

    private String activityEnddate;

    private Integer activityFee;

    private Integer activityPeoplelimit;

    private String activityAddress;

    private String activityAddressname;

    private String activityLongitude;

    private String activityLatitude;

    private String activityOrganizer;

    private String activityOrganizerphonenumber;


    public ActivityInfo2Back() {
    }

    public ActivityInfo2Back(String userIdMd5, Integer activityType, String activitySubject, String activityContent, String activitySignstartdate, String activitySignenddate, String activityStartdate, String activityEnddate, Integer activityFee, Integer activityPeoplelimit, String activityAddress, String activityAddressname, String activityLongitude, String activityLatitude, String activityOrganizer, String activityOrganizerphonenumber) {
        this.userIdMd5 = userIdMd5;
        this.activityType = activityType;
        this.activitySubject = activitySubject;
        this.activityContent = activityContent;
        this.activitySignstartdate = activitySignstartdate;
        this.activitySignenddate = activitySignenddate;
        this.activityStartdate = activityStartdate;
        this.activityEnddate = activityEnddate;
        this.activityFee = activityFee;
        this.activityPeoplelimit = activityPeoplelimit;
        this.activityAddress = activityAddress;
        this.activityAddressname = activityAddressname;
        this.activityLongitude = activityLongitude;
        this.activityLatitude = activityLatitude;
        this.activityOrganizer = activityOrganizer;
        this.activityOrganizerphonenumber = activityOrganizerphonenumber;
    }
}
