package com.activityhelper.Enums;

/**
 * @author byene
 * @date 2019/2/10 6:01 AM
 */
public enum ManagerInfoStatusEnum {

    ACCOUNT_ERROR( 0, "账号已被注册" ),
    ACCOUNT_SUCCESS( 1, "账号成功注册" ),
    ACCOUNT_UPDATE_SUCCESS( 2, "账号更新成功" ),
    ACCOUNT_FIND_SUCCESS( 3, "账号列表获取成功" ),
    ACCOUNT_DELETE_SUCCESS( 4, "账号删除成功" ),
    IS_SUPERMANAGER( 5, "超级管理员" ),
    NOT_SUPERMANAGER( 6, "非超级管理员" ),
    ACCOUNT_SEARCH_SUCCESS( 7, "管理员搜寻成功" ),
    LOGIN_SUCCESS( 8, "登录成功" ),
    LOGIN_FAILURE( 9, "登录失败" ),
    LOGOUT_SUCEESS( 10, "注销成功" ),
    LOGOUT_FAILURE( 11, "注销失败" ),
    SUPER_LOGIN_SUCCESS( 12, "超级管理员登录成功" ),
    ORDINARY_LOGIN_SUCCESS( 13, "普通管理员登录成功" ),
    ;
    private Integer code;

    private String message;

    ManagerInfoStatusEnum(Integer code, String message) {
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
