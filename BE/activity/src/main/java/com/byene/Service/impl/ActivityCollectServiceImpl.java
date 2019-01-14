package com.byene.Service.impl;

import com.byene.Dao.ActivityCollect;
import com.byene.Repository.ActivityCollectRepository;
import com.byene.Service.ActivityCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author byene
 * @date 2019/1/13 9:32 PM
 */
@Service
public class ActivityCollectServiceImpl implements ActivityCollectService {

    @Autowired
    ActivityCollectRepository activityCollectRepository;

    @Override
    public void save(ActivityCollect activityCollect) {
        activityCollectRepository.save( activityCollect );
    }

    @Override
    public List<ActivityCollect> FindallByUserId( String userId ) {
        return activityCollectRepository.findAllByUsercollectUseridIn( userId );
    }

    @Override
    public void delete(ActivityCollect activityCollect) {
        activityCollectRepository.delete( activityCollect );
    }

    @Override
    public List<ActivityCollect> FindallByActivityId(Integer activityId) {
        return activityCollectRepository.findAllByUsercollectActivityidIn( activityId );
    }
}
