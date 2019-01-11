package com.byene.Enums;

/**
 * @author byene
 * @date 2019/1/11 9:56 PM
 */
public enum ManagerInfoStatusEnum {

    ACCOUNT_ERROR( 0, "账号已被注册" ),
    ACCOUNT_SUCCESS( 1, "账号成功注册" ),
    ACCOUNT_UPDATE_SUCCESS( 2, "账号更新成功" ),
    ACCOUNT_FIND_SUCCESS( 3, "账号列表获取成功" ),
    ACCOUNT_DELETE_SUCCESS( 4, "账号删除成功" ),
    IS_SUPERMANAGER( 5, "超级管理员" ),
    NOT_SUPERMANAGER( 6, "非超级管理员" ),
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
