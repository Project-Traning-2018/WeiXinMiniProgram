package com.byene.Enums;

/**
 * @author byene
 * @date 2019/1/11 10:12 AM
 */
public enum ActivityInfoStatusEnum {

    ACTIVITY_EMPTY( 0, "初始化报名人数为空" ),
    ACTIVITY_VALID( 1, "活动有效" ),
    ACTIVITY_ADD_SUCCESS( 2, "活动添加成功" ),
    ACTIVITY_SEARCH_SUCCESS( 3, "活动查询成功" ),
    ;

    private Integer code;

    private String message;

    ActivityInfoStatusEnum() {
    }

    ActivityInfoStatusEnum(Integer code, String message) {
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
