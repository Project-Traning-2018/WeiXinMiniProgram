package com.byene.Service.impl;

import com.byene.Dao.ActivityMember;
import com.byene.Repository.ActivityMemberRepository;
import com.byene.Service.ActivityMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/12 3:22 PM
 */
@Service
@Slf4j
public class ActivityMemberServiceImpl implements ActivityMemberService {

    @Autowired
    ActivityMemberRepository activityMemberRepository;

    @Override
    public void save(ActivityMember activityMember) {

        activityMemberRepository.save( activityMember );
    }

    @Override
    public void delete(ActivityMember activityMember) {

        activityMemberRepository.delete( activityMember );
    }

    @Override
    public void deleteOneByUserId(String userId, Integer ActivityId) {

        List< ActivityMember > activityMemberList = activityMemberRepository.findAllByActivityUseridIn( userId );
        ActivityMember activityMember = new ActivityMember();

        for( ActivityMember key: activityMemberList )
        {
            if( key.getActivityActivityid().equals( ActivityId ) )
            {
                activityMember = key;
                break;
            }
        }
        log.info( "待删除用户参与活动信息： " + activityMember.toString() );
        activityMemberRepository.delete( activityMember );
    }

    @Override
    public List< ActivityMember > findallByActivityId(Integer activityId) {
        return activityMemberRepository.findAllByActivityActivityidIn( activityId );
    }
}
