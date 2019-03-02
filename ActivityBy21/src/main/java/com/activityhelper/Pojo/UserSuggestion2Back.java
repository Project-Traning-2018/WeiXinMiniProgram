package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/13 7:21 PM
 */
@Data
public class UserSuggestion2Back {

    /*前端用户登录凭证对应值*/
    private  String userIdMd5;

    private  String suggestion;

    public UserSuggestion2Back() {
    }

    public UserSuggestion2Back(String userIdMd5, String suggestion) {
        this.userIdMd5 = userIdMd5;
        this.suggestion = suggestion;
    }
}
