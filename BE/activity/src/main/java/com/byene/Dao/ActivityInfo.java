package com.byene.Dao;

import com.byene.Enums.ActivityInfoStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.DateTimeException;

/**
 * @author byene
 * @date 2019/1/11 9:06 AM
 */
@Entity
@Table( name = "activity_info" )
@Data
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

    private Integer activityValid = ActivityInfoStatusEnum.ACTIVITY_VALID.getCode();

    public ActivityInfo() {
    }

    public ActivityInfo(Integer activityType, String activitySubject, String activityContent, Timestamp activitySignstartdate, Timestamp activitySignenddate, Timestamp activityStartdate, Timestamp activityEnddate, Integer activityFee, Integer activityPeoplelimit, Integer activityPeopleregistered, String activityAddress, String activityAddressname, BigDecimal activityLongitude, BigDecimal activityLatitude, String activityOrganizer, String activityOrganizerphonenumber, String activityOrganizerid, Integer activityValid) {
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
    }
}
