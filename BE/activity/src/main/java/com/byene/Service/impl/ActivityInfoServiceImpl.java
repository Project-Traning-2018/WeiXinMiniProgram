package com.byene.Service.impl;

import com.byene.Dao.ActivityInfo;
import com.byene.Repository.ActivityInfoRepository;
import com.byene.Service.ActivityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/11 11:01 AM
 */
@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {

    @Autowired
    ActivityInfoRepository activityInfoRepository;

    @Override
    @Transactional
    public void save(ActivityInfo activityInfo) {
        activityInfoRepository.save( activityInfo );
    }

    @Override
    @Transactional
    public List<ActivityInfo> findall()
    {
        return activityInfoRepository.findAll();
    }

    @Override
    @Transactional
    public List<ActivityInfo> FindAllbyId(String userId) {
        return activityInfoRepository.findAllByActivityOrganizeridIn( userId );
    }

    @Override
    @Transactional
    public ActivityInfo FindOnebyId(Integer activityId) {
        return activityInfoRepository.getOne( activityId );
    }

    @Override
    @Transactional
    public void delete(Integer activityId) {

        ActivityInfo activityInfo = activityInfoRepository.getOne( activityId );
        activityInfoRepository.delete( activityInfo );
    }
}
