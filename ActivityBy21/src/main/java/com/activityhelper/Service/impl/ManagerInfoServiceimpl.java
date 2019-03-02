package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Conversion.InfoMd5;
import com.activityhelper.Dao.ManagerInfo;
import com.activityhelper.Enums.ManagerInfoStatusEnum;
import com.activityhelper.Pojo.ManagerInfo2Back;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Repository.ManagerInfoRepository;
import com.activityhelper.Service.ManagerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/13 7:57 PM
 */
@Service
@Slf4j
public class ManagerInfoServiceimpl implements ManagerInfoService {

    @Autowired
    ManagerInfoRepository managerInfoRepository;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Autowired
    InfoMd5     infoMd5;


    @Override
    @Transactional
    public void save(ManagerInfo managerInfo) {

        managerInfoRepository.save( managerInfo );
    }

    @Override
    @Transactional
    public void delete(String managerLoginno) {

        ManagerInfo managerInfo = managerInfoRepository.findByManagerLoginnoIn( managerLoginno );
        managerInfoRepository.delete( managerInfo );
    }

    @Override
    @Transactional
    public void update(ManagerInfo managerInfo) {

        managerInfoRepository.save( managerInfo );
    }

    /*如果是超级管理员,可以返回其余的全部管理员信息，否则返回为空*/
    @Override
    @Transactional
    public List<ManagerInfo> findall(String managerLoginno) {

        List< ManagerInfo > managerInfoList = new ArrayList<>();
        List< ManagerInfo > managerInfoListwithoutRoot = new ArrayList<>();
        if( managerLoginno.equals( "root" ) )
        {
            managerInfoList = managerInfoRepository.findAll();
            for( ManagerInfo key: managerInfoList )
            {
                if( key.getManagerLoginno().equals( "root" ) ) continue;
                managerInfoListwithoutRoot.add( key );
            }
        }
        return managerInfoListwithoutRoot;

    }

    @Override
    @Transactional
    public ManagerInfo findbymanagerId(String managerLoginno) {

        return managerInfoRepository.findByManagerLoginnoIn( managerLoginno );
    }


    @Override
    @Transactional
    public ResultVO managerSave(ManagerInfo2Back managerInfo2Back)
    {
        /*获取注册的管理员账号*/
        String managerloginnumber = managerInfo2Back.getManagerLoginno();

        /*账号重复，返回失败*/
        if( findbymanagerId( managerloginnumber ) != null  )
            return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.ACCOUNT_ERROR.getCode(), ManagerInfoStatusEnum.ACCOUNT_ERROR.getMessage(), null );

        ManagerInfo managerInfo = new ManagerInfo();

        /*对密码进行加密*/
        String PasswordMd5 = infoMd5.String2Md5( managerInfo2Back.getManagerPassword() );

        /*将managerInfo2Back复制到managerInfo中*/
        managerInfo.setManagerNickname( managerInfo2Back.getManagerNickname() );
        managerInfo.setManagerLoginno( managerInfo2Back.getManagerLoginno() );
        managerInfo.setManagerPassword( PasswordMd5 );

        save( managerInfo );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.ACCOUNT_SUCCESS.getCode(), ManagerInfoStatusEnum.ACCOUNT_SUCCESS.getMessage(), null );

    }

    @Override
    public ResultVO managerUpdate(ManagerInfo2Back managerInfo2Back)
    {
        /*获取更新的管理员账号*/
        String ManagerLoginno = managerInfo2Back.getManagerLoginno();

        /*由管理员账号获取完整信息*/
        ManagerInfo managerInfo = findbymanagerId( ManagerLoginno );

        /*对密码进行加密*/
        String PasswordMd5 = infoMd5.String2Md5( managerInfo2Back.getManagerPassword() );

        /*更新账号信息*/
        managerInfo.setManagerNickname( managerInfo2Back.getManagerNickname() );
        managerInfo.setManagerLoginno( managerInfo2Back.getManagerLoginno() );
        managerInfo.setManagerPassword( PasswordMd5 );

        save( managerInfo );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.ACCOUNT_UPDATE_SUCCESS.getCode(), ManagerInfoStatusEnum.ACCOUNT_UPDATE_SUCCESS.getMessage(), null );

    }

    @Override
    public ResultVO managerInfoList(String managerLoginno)
    {
        List< ManagerInfo > managerInfoList = findall( managerLoginno );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.ACCOUNT_FIND_SUCCESS.getCode(), ManagerInfoStatusEnum.ACCOUNT_FIND_SUCCESS.getMessage(), managerInfoList  );
    }

    @Override
    public ResultVO managerInfoDelete(String managerLoginno)
    {
        delete( managerLoginno );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.ACCOUNT_DELETE_SUCCESS.getCode(), ManagerInfoStatusEnum.ACCOUNT_DELETE_SUCCESS.getMessage(), null );
    }

    @Override
    public ResultVO supermanagerCheck(String managerLoginno)
    {
        if( managerLoginno.equals( "root" ) )
            return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.IS_SUPERMANAGER.getCode(), ManagerInfoStatusEnum.IS_SUPERMANAGER.getMessage(), null );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.NOT_SUPERMANAGER.getCode(), ManagerInfoStatusEnum.NOT_SUPERMANAGER.getMessage(), null );
    }

    @Override
    public ResultVO SearchmanagerList(String str) {

        List< ManagerInfo > managerInfoList = managerInfoRepository.findAllByManagerLoginnoContaining( str );

        return getResultVOInfo.GetResultVO( ManagerInfoStatusEnum.ACCOUNT_SEARCH_SUCCESS.getCode(), ManagerInfoStatusEnum.ACCOUNT_SEARCH_SUCCESS.getMessage(), managerInfoList );

    }
}
