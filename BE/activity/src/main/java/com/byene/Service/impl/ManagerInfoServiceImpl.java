package com.byene.Service.impl;

import com.byene.Dao.ManagerInfo;
import com.byene.Repository.ManagerInfoRepository;
import com.byene.Service.ManagerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author byene
 * @date 2019/1/11 5:24 PM
 */

@Service
@Slf4j
public class ManagerInfoServiceImpl implements ManagerInfoService {

    @Autowired
    ManagerInfoRepository managerInfoRepository;

    @Override
    public void save(ManagerInfo managerInfo) {
        managerInfoRepository.save( managerInfo );
    }

    @Override
    public void delete(String managerLoginno) {

        ManagerInfo managerInfo = managerInfoRepository.findByManagerLoginnoIn( managerLoginno );
        managerInfoRepository.delete( managerInfo );
    }

    @Override
    public void update(ManagerInfo managerInfo) {
        managerInfoRepository.save( managerInfo );
    }

    /*如果是超级管理员,可以返回其余的全部管理员信息，否则返回为空*/
    @Override
    public List<ManagerInfo> findall( String managerLoginno ) {

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
    public ManagerInfo findbymanagerId(String managerLoginno ) {
        return managerInfoRepository.findByManagerLoginnoIn( managerLoginno );
    }
}
