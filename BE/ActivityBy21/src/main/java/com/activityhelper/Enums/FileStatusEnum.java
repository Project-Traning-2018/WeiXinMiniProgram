package com.activityhelper.Enums;

/**
 * @author byene
 * @date 2019/2/10 6:01 AM
 */
public enum FileStatusEnum {

    FILE_EMPTY( 1, "文件为空" ),
    FILE_SUCCESS( 2, "文件上传成功" ),
    FILE_GET_SUCCESS( 3, "文件获取成功" ),
    ;
    private Integer code;

    private String message;

    FileStatusEnum(Integer code, String message) {
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
