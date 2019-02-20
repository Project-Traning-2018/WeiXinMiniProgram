package com.activityhelper.BackController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.UserInfoServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/19 10:05 AM
 */
@RestController
@RequestMapping( "/back" )
public class UserInfoController {

    @Autowired
    UserInfoServiceimpl userInfoService;

    @PostMapping( "/userinfo/list" )
    public ResultVO BackUserInfoFindAll()
    {
        return userInfoService.BackUserInfoFindAll();
    }

    @PostMapping( "/userinfo/search" )
    public ResultVO BackUserInfoSearch( @RequestParam("str") String str )
    {
        return userInfoService.BackUserInfoSearch( str );
    }
}
