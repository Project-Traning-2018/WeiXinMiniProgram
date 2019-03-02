package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/10 6:08 AM
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
