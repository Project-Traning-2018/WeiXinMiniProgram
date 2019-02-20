package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/10 5:43 AM
 */

@Data
public class ResultVO<T> {

    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*具体内容*/
    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
