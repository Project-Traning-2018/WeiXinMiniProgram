package com.activityhelper.Service.impl;

import com.activityhelper.Dao.UserparticipationInfo;
import com.activityhelper.Repository.UserparticipationInfoRepository;
import com.activityhelper.Service.UserparticipationInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/18 8:48 AM
 */
@Service
@Slf4j
public class UserparticipationInfoServiceimpl implements UserparticipationInfoService {

    @Autowired
    UserparticipationInfoRepository userparticipationInfoRepository;

    @Override
    @Transactional
    public void save(UserparticipationInfo userparticipationInfo) {

        userparticipationInfoRepository.save( userparticipationInfo );

    }

    @Override
    @Transactional
    public void delete(UserparticipationInfo userparticipationInfo) {

        userparticipationInfoRepository.delete( userparticipationInfo );

    }

    @Override
    @Transactional
    public UserparticipationInfo findone(Integer Activityid, String Userid) {

        List< UserparticipationInfo >  userparticipationInfoList = new ArrayList<>();

        userparticipationInfoList = userparticipationInfoRepository.findAllByUserparticipationActivityid( Activityid );

        UserparticipationInfo NewuserparticipationInfo = new UserparticipationInfo();

        for( UserparticipationInfo key : userparticipationInfoList  )
        {
            if( key.getUserparticipationUserid().equals( Userid ) )
            {
                NewuserparticipationInfo = key;
                break;
            }
        }
        return NewuserparticipationInfo;
    }

    @Override
    @Transactional
    public List<UserparticipationInfo> findAllByUserparticipationActivityid(Integer Activityid) {
        return userparticipationInfoRepository.findAllByUserparticipationActivityid( Activityid );
    }
}
