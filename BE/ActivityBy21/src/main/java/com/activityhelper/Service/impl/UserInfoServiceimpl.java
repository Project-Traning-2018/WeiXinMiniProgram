package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Dao.UserInfo;
import com.activityhelper.Enums.UserInfoStatusEnum;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Repository.UserInfoRepository;
import com.activityhelper.Service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/11 4:34 AM
 */
@Service
@Slf4j
public class UserInfoServiceimpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Override
    @Transactional
    public void save(UserInfo userInfo) {
        userInfoRepository.save( userInfo );
    }

    @Override
    @Transactional
    public void delete(String userId) {

        UserInfo userInfo = userInfoRepository.getOne( userId );
        if( userInfo == null ) return;
        userInfoRepository.delete( userInfo );
    }

    @Override
    @Transactional
    public UserInfo FindOneById(String userId) {
        return userInfoRepository.getOne( userId );
    }

    @Override
    @Transactional
    public List<UserInfo> FindAll() {
        return userInfoRepository.findAll();
    }

    @Override
    @Transactional
    public List<UserInfo> FindAllbyPage(Integer Page, Integer Size) {

        Pageable pageable = PageRequest.of( Page, Size );

        org.springframework.data.domain.Page< UserInfo > userInfoPage = userInfoRepository.findAll( pageable );

        List<UserInfo> userInfoList = userInfoPage.getContent();

        return userInfoList;

    }

    @Override
    @Transactional
    public ResultVO BackUserInfoFindAll() {

        List< UserInfo > userInfoList = FindAll();
        return getResultVOInfo.GetResultVO(UserInfoStatusEnum.BACK_USERINFO_LIST.getCode(), UserInfoStatusEnum.BACK_USERINFO_LIST.getMessage(), userInfoList);

    }

    @Override
    @Transactional
    public ResultVO BackUserInfoSearch(String str) {

        List< UserInfo > userInfoList = userInfoRepository.findAllByUserNameContaining( str );
        return getResultVOInfo.GetResultVO( UserInfoStatusEnum.BACK_USERINFO_LIST.getCode(), UserInfoStatusEnum.BACK_USERINFO_LIST.getMessage(), userInfoList );
    }
}
