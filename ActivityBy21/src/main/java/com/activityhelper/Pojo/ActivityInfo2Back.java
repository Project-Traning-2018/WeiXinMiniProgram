package com.activityhelper.Pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author byene
 * @date 2019/2/15 8:04 PM
 */
@Data
public class ActivityInfo2Back {

    /*前端用户登录凭证对应值*/
    private String userIdMd5;

    private Integer activityType;

    private String activityContent;

    private String activitySubject;

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

    private String fileurl1;

    private String fileurl2;

    private String fileurl3;

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

    public ActivityInfo2Back() {
    }

    public ActivityInfo2Back(String userIdMd5, Integer activityType, String activityContent, String activitySubject, String activitySignstartdate, String activitySignenddate, String activityStartdate, String activityEnddate, Integer activityFee, Integer activityPeoplelimit, String activityAddress, String activityAddressname, String activityLongitude, String activityLatitude, String activityOrganizer, String activityOrganizerphonenumber, String fileurl1, String fileurl2, String fileurl3, String activityparticipationItem1, String activityparticipationItem2, String activityparticipationItem3, String activityparticipationItem4, String activityparticipationItem5, String activityparticipationItem6, String activityparticipationItem7, String activityparticipationItem8, String activityparticipationItem9, String activityparticipationItem10) {
        this.userIdMd5 = userIdMd5;
        this.activityType = activityType;
        this.activityContent = activityContent;
        this.activitySubject = activitySubject;
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
        this.fileurl1 = fileurl1;
        this.fileurl2 = fileurl2;
        this.fileurl3 = fileurl3;
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
