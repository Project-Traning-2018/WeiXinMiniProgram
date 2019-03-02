package com.activityhelper.Enums;

/**
 * @author byene
 * @date 2019/2/19 10:10 AM
 */
public enum UserInfoStatusEnum {

    BACK_USERINFO_LIST( 1, "用户信息列表获取成功" ),
    BACK_USERINFO_SEARCH( 2, "用户信息搜寻成功" ),
    ;
    private Integer code;

    private String message;

    UserInfoStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
