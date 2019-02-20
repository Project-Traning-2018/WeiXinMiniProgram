package com.activityhelper.Enums;

/**
 * @author byene
 * @date 2019/2/10 6:52 AM
 */
public enum BackInfoStausEnum {

    BACK_USER_SUCCESS( 1, "用户信息列表读取成功" ),
    BACK_EMPTY( 2, "该页无信息" ),
    BACK_SUGGESTION_SUCCESS( 3, "建议列表读取成功" ),
    BACK_SUGGESTION_DELETE_SUCCESS( 4, "建议删除成功" ),
    BACK_ACTIVITY_SUCCESS( 5, "活动信息列表读取成功" ),
    BACK_SUGGESTION_GETONE_SUCCESS( 6, "一条建议列表读取成功" ),
    BACK_SUGGESTION_SEARCH_SUCCESS( 7, "建议列表搜寻成功" ),
    ;

    private Integer code;

    private String message;

    BackInfoStausEnum(Integer code, String message) {
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
