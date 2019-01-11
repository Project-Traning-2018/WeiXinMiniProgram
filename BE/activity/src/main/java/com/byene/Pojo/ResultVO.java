package com.byene.Pojo;

import lombok.Data;

/**
 * http返回的最外层Json
 * @author byene
 * @date 2019/1/8 8:41 PM
 */

@Data
public class ResultVO<T>{

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
