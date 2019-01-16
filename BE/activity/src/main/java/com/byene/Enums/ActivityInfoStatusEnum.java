package com.byene.Enums;

/**
 * @author byene
 * @date 2019/1/11 10:12 AM
 */
public enum ActivityInfoStatusEnum {

    ACTIVITY_EMPTY( 0, "初始化报名人数为空" ),
    ACTIVITY_VALID( 1, "活动报名有效" ),
    ACTIVITY_INVALID( 2, "活动报名无效" ),
    ACTIVITY_ADD_SUCCESS( 3, "活动添加成功" ),
    ACTIVITY_SEARCH_SUCCESS( 4, "活动查询成功" ),
    ACTIVITY_PEOPLE_FULL( 5, "活动人数已满" ),
    ACTIVITY_PARTICIPATE_SUCCESS( 6, "活动报名成功" ),
    ACTIVITY_DELETE_SUCCESS( 7, "活动删除成功" ),
    ACTIVITY_PARTICIPATE_CANCEL_SUCCESS( 8, "活动取消参加成功" ),
    ACTIVITY_ORGANIZER_ERROR( 9, "无法参加自己已发布活动" ),
    ACTIVITY_PARTICIPATE_ERROR( 10, "无法重复报名" ),
    ACTIVITY_NEARBY_SUCCESS( 11, "获取附近活动信息成功" ),
    ACTIVITY_PARTICIPATE_LIST_SUCCESS( 12, "获取参加活动列表信息成功" ),
    ACTIVITY_COLLECT_SUCCESS( 13, "活动收藏成功" ),
    ACTIVITY_COLLECT_LIST_SUCCESS( 14, "获取收藏活动列表信息成功" ),
    ACTIVITY_COLLECT_DELETE_SUCCESS( 15, "删除收藏活动成功" ),
    ACTIVITY_PEOPLEREGISTERED_SUCCESS( 16, "获取活动已报名人数成功" ),
    ACTIVITY_COLLECT_ERROR( 17, "无法重复收藏" ),
    ACTIVITY_UPDATE_SUCCESS( 18, "活动更新成功" ),
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
