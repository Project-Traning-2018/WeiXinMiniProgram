package com.byene.Repository;

import com.byene.Conversion.TimeTransfer;
import com.byene.Dao.ActivityInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * @author byene
 * @date 2019/1/11 9:26 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityInfoRepositoryTest {

    @Autowired
    ActivityInfoRepository activityInfoRepository;

    @Autowired
    TimeTransfer timeTransfer;

    @Test
    public void save()
    {
        ActivityInfo activityInfo = new ActivityInfo();

        activityInfo.setActivityType( 1 );
        activityInfo.setActivitySubject( "觅食" );
        activityInfo.setActivityContent( "吃吃吃吃吃吃吃吃吃吃吃" );
        activityInfo.setActivitySignstartdate( timeTransfer.String2Timestamp( "2019-1-11 10:00:00" ) );
        activityInfo.setActivitySignenddate( timeTransfer.String2Timestamp( "2019-1-13 10:00:00" ) );
        activityInfo.setActivityStartdate( timeTransfer.String2Timestamp( "2019-1-15 10:00:00" ) );
        activityInfo.setActivityEnddate( timeTransfer.String2Timestamp( "2019-1-15 13:00:00" ) );
        activityInfo.setActivityFee( 5 );
        activityInfo.setActivityPeoplelimit( 50 );
        activityInfo.setActivityAddress( "华中科技大学" );
        activityInfo.setActivityAddressname( "软件学院" );
        activityInfo.setActivityLatitude( BigDecimal.valueOf( 10.12 ) );
        activityInfo.setActivityLongitude( BigDecimal.valueOf( 10.13 ) );
        activityInfo.setActivityOrganizer( "小华" );
        activityInfo.setActivityOrganizerphonenumber( "18062605076" );
        activityInfo.setActivityOrganizerid( "123455678byene" );
        log.info( activityInfo.toString() );
        activityInfoRepository.save( activityInfo );
    }


}