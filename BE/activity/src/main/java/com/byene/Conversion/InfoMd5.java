package com.byene.Conversion;

import com.byene.Pojo.WxInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * 对用户信息进行MD5加密
 * @author byene
 * @date 2019/1/8 8:56 PM
 */
@Component
public class InfoMd5 {

    private static final String slat = "&%5123***&&%%$$#@";

    public String getMd5( WxInfo wxInfo )
    {
        String openId = wxInfo.getOpenid();
        String sessonKey = wxInfo.getSession_key();
        String base = openId + sessonKey + "/"+slat;
        return DigestUtils.md5DigestAsHex( base.getBytes() );
    }
}
