package com.byene.Pojo;

import lombok.Data;

/**
 * 微信服务器端发来的信息实例化为类
 * @author byene
 * @date 2019/1/8 8:22 PM
 */
@Data
public class WxInfo {

    private String openid;
    private String session_key;

    public WxInfo()
    {

    }

    public WxInfo(String openid, String session_key)
    {
        this.openid = openid;
        this.session_key = session_key;
    }
}
