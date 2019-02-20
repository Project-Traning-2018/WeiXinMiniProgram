package com.activityhelper.Enums;

/**
 * @author byene
 * @date 2019/2/18 10:14 AM
 */
public enum UserparticipationStatusEnum {

    USERPARTICIPATION_SAVE_SUCCESS( 0, "用户报名信息存储成功" ),
    USERPARTICIPATION_UPDATE_SUCCESS( 1, "用户报名信息更新成功" ),
    USERPARTICIPATION_DELETE_SUCCESS( 2, "用户报名信息删除成功" ),
    ;

    private Integer code;

    private String message;

    UserparticipationStatusEnum(Integer code, String message) {
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
