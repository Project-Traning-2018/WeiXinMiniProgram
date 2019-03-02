package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Conversion.InfoMd5;
import com.activityhelper.Dao.ManagerInfo;
import com.activityhelper.Enums.ManagerInfoStatusEnum;
import com.activityhelper.Pojo.BackManagerInfo;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.LoginService;
import com.activityhelper.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author byene
 * @date 2019/2/19 2:22 PM
 */
@Service
@Slf4j
public class LoginServiceimpl implements LoginService {

    @Autowired
    ManagerInfoServiceimpl infoServiceimpl;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Autowired
    InfoMd5 infoMd5;

    @Override
    @Transactional
    public ResultVO Login(String UserLog, String Password) {

        ManagerInfo managerInfo = infoServiceimpl.findbymanagerId( UserLog );

        if( managerInfo == null )
            return getResultVOInfo.GetResultVO(ManagerInfoStatusEnum.LOGIN_FAILURE.getCode(), ManagerInfoStatusEnum.LOGIN_FAILURE.getMessage(), null);

        String PasswordMd5 = infoMd5.String2Md5( Password );

        if( !managerInfo.getManagerPassword().equals( PasswordMd5 ) )
            return getResultVOInfo.GetResultVO(ManagerInfoStatusEnum.LOGIN_FAILURE.getCode(), ManagerInfoStatusEnum.LOGIN_FAILURE.getMessage(), null);

        BackManagerInfo backManagerInfo = new BackManagerInfo();

        backManagerInfo.setManagerLoginno( UserLog );
        backManagerInfo.setManagerPassword( PasswordMd5 );

        strRedis.opsForValue().set( UserLog, JsonUtils.objectToJson( backManagerInfo ),60*100, TimeUnit.SECONDS );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.LOGIN_SUCCESS.getCode(), ManagerInfoStatusEnum.LOGIN_SUCCESS.getMessage(), null );

    }

    @Override
    @Transactional
    public ResultVO Logout(String UserLog) {

        strRedis.delete( UserLog );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.LOGOUT_SUCEESS.getCode(), ManagerInfoStatusEnum.LOGOUT_SUCEESS.getMessage(), null );

    }
}
