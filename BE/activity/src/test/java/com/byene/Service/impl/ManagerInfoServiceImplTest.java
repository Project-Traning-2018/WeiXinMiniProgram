package com.byene.Service.impl;

import com.byene.Dao.ManagerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author byene
 * @date 2019/1/11 10:30 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ManagerInfoServiceImplTest {

    @Autowired
    ManagerInfoServiceImpl managerInfoService;

    @Test
    public void save(){
        ManagerInfo managerInfo = new ManagerInfo();
        managerInfo.setManagerNickname( "test1" );
        managerInfo.setManagerLoginno( "TEST1" );
        managerInfo.setManagerPassword( "123456" );
        managerInfoService.save( managerInfo );
    }

    @Test
    public void delete() {
        managerInfoService.delete( "TEST1" );
    }

    @Test
    public void update() {
        ManagerInfo managerInfo = new ManagerInfo();
        managerInfo.setManagerId( 6 );
        managerInfo.setManagerNickname( "test1" );
        managerInfo.setManagerLoginno( "TEST1" );
        managerInfo.setManagerPassword( "1234567" );
        managerInfoService.save( managerInfo );
    }

    @Test
    public void findall() {
        List< ManagerInfo > managerInfoList =  managerInfoService.findall( "test1" );
        System.out.println( managerInfoList.size() );
    }

    @Test
    public void findbymanagerId() {
    }
}