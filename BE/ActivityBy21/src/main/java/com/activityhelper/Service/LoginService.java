package com.activityhelper.Service;

import com.activityhelper.Pojo.ResultVO;

/**
 * @author byene
 * @date 2019/2/19 2:21 PM
 */
public interface LoginService {

    /*登录*/
    ResultVO Login( String UserLog, String Password );

    /*注销*/
    ResultVO Logout( String UserLog );
}
