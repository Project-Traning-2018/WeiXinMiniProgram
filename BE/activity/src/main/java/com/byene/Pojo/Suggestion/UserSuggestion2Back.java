package com.byene.Pojo.Suggestion;

import lombok.Data;

/**
 * 用户建议和登录状态的实体类
 * @author byene
 * @date 2019/1/8 9:43 PM
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
