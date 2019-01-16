package com.byene.Pojo.Activity;

import lombok.Data;

/**
 * @author byene
 * @date 2019/1/12 2:43 PM
 */
@Data
public class ActivityChooseInfo2Back {

    /*前端用户登录凭证对应值*/
    private String userIdMd5;

    /*前端用户选择活动Id*/
    private Integer activityId;


    public ActivityChooseInfo2Back() {
    }

    public ActivityChooseInfo2Back(String userIdMd5, Integer activityId) {
        this.userIdMd5 = userIdMd5;
        this.activityId = activityId;
    }


}
