package com.activityhelper.FrontController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.UserInfo2Back;
import com.activityhelper.Service.impl.WxInfoServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author byene
 * @date 2019/2/10 6:05 AM
 */

@RestController
@RequestMapping( "/wx" )
@Slf4j
public class WxController {

    @Autowired
    WxInfoServiceimpl wxInfoServiceimpl;


    @PostMapping( "/wxlogin" )
    public ResultVO WxLogin( @RequestParam("code") String code )
    {
        return wxInfoServiceimpl.Wxlogin( code );
    }

    @PostMapping( "wxuserinfo" )
    public ResultVO WxUserinfo( @RequestBody UserInfo2Back userInfo2Back )
    {
        return wxInfoServiceimpl.WxUserinfo( userInfo2Back );
    }

    @PostMapping( "wxuserinfoupdate" )
    public ResultVO WxUserinfoUpdate( @RequestBody UserInfo2Back userInfo2Back )
    {
        return wxInfoServiceimpl.WxUserinfoUpdate( userInfo2Back );
    }

    @PostMapping( "/getuserinfo" )
    public ResultVO GetUserInfo( @RequestParam("userKey") String userKey )
    {
        return wxInfoServiceimpl.GetUserInfo( userKey );
    }

}
