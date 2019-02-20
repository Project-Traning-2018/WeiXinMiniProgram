package com.activityhelper.BackController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.LoginServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/19 11:38 AM
 */

@RestController
@RequestMapping( "/back" )
public class LoginController {

    @Autowired
    LoginServiceimpl loginService;

    @PostMapping( "/login" )
    public ResultVO Login(@RequestParam("UserLog") String UserLog, @RequestParam("Password") String Password)
    {
        return loginService.Login( UserLog, Password );
    }

}
