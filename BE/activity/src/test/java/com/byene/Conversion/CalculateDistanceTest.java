package com.byene.Conversion;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author byene
 * @date 2019/1/16 9:14 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateDistanceTest {

    @Autowired
    CalculateDistance calculateDistance;

    @Test
    public void getDistance() {
        System.out.println( calculateDistance.getDistance( 114.419389,30.513092,114.363428, 30.535444  ) );
    }
}