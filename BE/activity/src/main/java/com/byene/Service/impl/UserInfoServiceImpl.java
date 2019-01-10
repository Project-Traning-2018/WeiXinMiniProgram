package com.byene.Service.impl;

import com.byene.Dao.UserInfo;
import com.byene.Repository.UserInfoRepository;
import com.byene.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/8 7:54 PM
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

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

        Page< UserInfo > userInfoPage = userInfoRepository.findAll( pageable );

        List<UserInfo> userInfoList = userInfoPage.getContent();

        return userInfoList;

    }
}
