package com.byene.Enums;

/**
 * @author byene
 * @date 2019/1/11 10:12 AM
 */
public enum ActivityInfoStatusEnum {

    ACTIVITY_EMPTY( 0, "初始化报名人数为空" ),
    ACTIVITY_VALID( 1, "活动有效" ),
    ACTIVITY_INVALID( 2, "活动无效" ),
    ACTIVITY_ADD_SUCCESS( 3, "活动添加成功" ),
    ACTIVITY_SEARCH_SUCCESS( 4, "活动查询成功" ),
    ACTIVITY_PEOPLE_FULL( 5, "活动人数已满" ),
    ACTIVITY_PARTICIPATE_SUCCESS( 6, "活动报名成功" ),
    ACTIVITY_DELETE_SUCCESS( 7, "活动删除成功" ),
    ACTIVITY_PARTICIPATE_CANCEL_SUCCESS( 8, "活动取消参加成功" ),
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
