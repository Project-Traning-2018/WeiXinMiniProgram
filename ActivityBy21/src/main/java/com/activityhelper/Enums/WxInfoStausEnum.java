package com.activityhelper.Enums;

/**
 * @author byene
 * @date 2019/2/10 6:00 AM
 */
public enum WxInfoStausEnum {

    WX_SUCCESS( 1, "身份验证成功" ),
    WX_ERROR( 2, "身份已过期" ),
    WX_UPDATE( 3, "用户信息更新成功" ),
    WX_GET( 4, "用户信息获取成功" ),
    SUGGESTION_SUCCESS( 5, "用户建议提交成功" ),
    WX_SAVE( 6, "用户信息保存成功" ),
    ;

    private Integer code;

    private String message;

    WxInfoStausEnum(Integer code, String message) {
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
