package com.activityhelper.Service;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.UserInfo2Back;

/**
 * @author byene
 * @date 2019/2/10 6:27 AM
 */
public interface WxInfoService {

    /*获取微信信息*/
    ResultVO Wxlogin( String code );


    /*保存用户信息*/
    ResultVO WxUserinfo( UserInfo2Back userInfo2Back );

    /*用户信息更新*/
    ResultVO WxUserinfoUpdate( UserInfo2Back userInfo2Back );

    /*获取用户信息*/
    ResultVO GetUserInfo( String userKey );
}
