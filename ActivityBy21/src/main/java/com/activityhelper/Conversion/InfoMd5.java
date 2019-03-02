package com.activityhelper.Conversion;

import com.activityhelper.Pojo.WxInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author byene
 * @date 2019/2/10 6:07 AM
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

    public String String2Md5( String name )
    {
        String base = name + "/" + slat;
        return DigestUtils.md5DigestAsHex( base.getBytes() );
    }
}
