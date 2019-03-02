package com.activityhelper.Pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author byene
 * @date 2019/2/16 7:44 PM
 */
@Data
public class ActivityInfoUpdate2Back {

    /*前端用户登录凭证对应值*/
    private String userIdMd5;

    private Integer activityId;

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

    private String fileurl1;

    private String fileurl2;

    private String fileurl3;

    public ActivityInfoUpdate2Back() {
    }

    public ActivityInfoUpdate2Back(String userIdMd5, Integer activityId, Integer activityType, String activitySubject, String activityContent, String activitySignstartdate, String activitySignenddate, String activityStartdate, String activityEnddate, Integer activityFee, Integer activityPeoplelimit, String activityAddress, String activityAddressname, String activityLongitude, String activityLatitude, String activityOrganizer, String activityOrganizerphonenumber, String fileurl1, String fileurl2, String fileurl3) {
        this.userIdMd5 = userIdMd5;
        this.activityId = activityId;
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
        this.fileurl1 = fileurl1;
        this.fileurl2 = fileurl2;
        this.fileurl3 = fileurl3;
    }
}
