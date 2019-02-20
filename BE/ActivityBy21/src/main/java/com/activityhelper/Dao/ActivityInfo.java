package com.activityhelper.Dao;

import com.activityhelper.Enums.ActivityInfoStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author byene
 * @date 2019/2/10 5:03 AM
 */
@Entity
@Table( name = "activity_info" )
@Data
@JsonIgnoreProperties( {"hibernateLazyInitializer","handler"} )
public class ActivityInfo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer activityId;

    private Integer activityType;

    private String activitySubject;

    private String activityContent;

    private Timestamp activitySignstartdate;

    private Timestamp activitySignenddate;

    private Timestamp activityStartdate;

    private Timestamp activityEnddate;

    private Integer activityFee;

    private Integer activityPeoplelimit;

    private Integer activityPeopleregistered = ActivityInfoStatusEnum.ACTIVITY_EMPTY.getCode();

    private String activityAddress;

    private String activityAddressname;

    private BigDecimal activityLongitude;

    private BigDecimal activityLatitude;

    private String activityOrganizer;

    private String activityOrganizerphonenumber;

    private String activityOrganizerid;

    private Integer activityValid = ActivityInfoStatusEnum.ACTIVITY_STATUS_VALID.getCode();

    private String activityImageurl1;

    private String activityImageurl2;

    private String activityImageurl3;

    private String activityparticipationItem1;

    private String activityparticipationItem2;

    private String activityparticipationItem3;

    private String activityparticipationItem4;

    private String activityparticipationItem5;

    private String activityparticipationItem6;

    private String activityparticipationItem7;

    private String activityparticipationItem8;

    private String activityparticipationItem9;

    private String activityparticipationItem10;

    public ActivityInfo() {
    }

    public ActivityInfo(Integer activityType, String activitySubject, String activityContent, Timestamp activitySignstartdate, Timestamp activitySignenddate, Timestamp activityStartdate, Timestamp activityEnddate, Integer activityFee, Integer activityPeoplelimit, Integer activityPeopleregistered, String activityAddress, String activityAddressname, BigDecimal activityLongitude, BigDecimal activityLatitude, String activityOrganizer, String activityOrganizerphonenumber, String activityOrganizerid, Integer activityValid, String activityImageurl1, String activityImageurl2, String activityImageurl3, String activityparticipationItem1, String activityparticipationItem2, String activityparticipationItem3, String activityparticipationItem4, String activityparticipationItem5, String activityparticipationItem6, String activityparticipationItem7, String activityparticipationItem8, String activityparticipationItem9, String activityparticipationItem10) {
        this.activityType = activityType;
        this.activitySubject = activitySubject;
        this.activityContent = activityContent;
        this.activitySignstartdate = activitySignstartdate;
        this.activitySignenddate = activitySignenddate;
        this.activityStartdate = activityStartdate;
        this.activityEnddate = activityEnddate;
        this.activityFee = activityFee;
        this.activityPeoplelimit = activityPeoplelimit;
        this.activityPeopleregistered = activityPeopleregistered;
        this.activityAddress = activityAddress;
        this.activityAddressname = activityAddressname;
        this.activityLongitude = activityLongitude;
        this.activityLatitude = activityLatitude;
        this.activityOrganizer = activityOrganizer;
        this.activityOrganizerphonenumber = activityOrganizerphonenumber;
        this.activityOrganizerid = activityOrganizerid;
        this.activityValid = activityValid;
        this.activityImageurl1 = activityImageurl1;
        this.activityImageurl2 = activityImageurl2;
        this.activityImageurl3 = activityImageurl3;
        this.activityparticipationItem1 = activityparticipationItem1;
        this.activityparticipationItem2 = activityparticipationItem2;
        this.activityparticipationItem3 = activityparticipationItem3;
        this.activityparticipationItem4 = activityparticipationItem4;
        this.activityparticipationItem5 = activityparticipationItem5;
        this.activityparticipationItem6 = activityparticipationItem6;
        this.activityparticipationItem7 = activityparticipationItem7;
        this.activityparticipationItem8 = activityparticipationItem8;
        this.activityparticipationItem9 = activityparticipationItem9;
        this.activityparticipationItem10 = activityparticipationItem10;
    }
}
