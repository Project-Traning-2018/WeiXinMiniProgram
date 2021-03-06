package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/17 9:33 PM
 */
@Data
public class ActivityChooseInfo2Back {

    /*前端用户登录凭证对应值*/
    private String userIdMd5;

    /*前端用户选择活动Id*/
    private Integer activityId;

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

    public ActivityChooseInfo2Back() {
    }

    public ActivityChooseInfo2Back(String userIdMd5, Integer activityId, String activityparticipationItem1, String activityparticipationItem2, String activityparticipationItem3, String activityparticipationItem4, String activityparticipationItem5, String activityparticipationItem6, String activityparticipationItem7, String activityparticipationItem8, String activityparticipationItem9, String activityparticipationItem10) {
        this.userIdMd5 = userIdMd5;
        this.activityId = activityId;
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
