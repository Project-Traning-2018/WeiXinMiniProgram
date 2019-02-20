package com.activityhelper.Conversion;

import com.activityhelper.Pojo.WxInfo;
import com.activityhelper.Utils.HttpClientUtils;
import com.activityhelper.Utils.JsonUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author byene
 * @date 2019/2/10 6:11 AM
 */
@Component
public class GetWxInfo {

    @Value( "${wx.appid}" )
    private String appid;

    @Value( "${wx.secret}" )
    private String secret;

    private static final String grant_type = "authorization_code";

    public WxInfo Get( String code )
    {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map< String, String > param = new HashMap<>();

        param.put( "appid", appid );
        param.put( "secret", secret );
        param.put( "js_code", code);
        param.put( "grant_type", grant_type );
        String wxResult =  HttpClientUtils.doGet( url, param );
        return JsonUtils.jsonToPojo( wxResult, WxInfo.class );
    }
}
