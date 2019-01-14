package com.byene.Conversion;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author byene
 * @date 2019/1/13 2:50 PM
 */
@Component
public class CalculateDistance {


    private static double EarthRadius = 6378.137;

    private static  double rad( double d )
    {
        return d * Math.PI / 180.0;
    }

    /*获取距离  单位： 公里/千米*/
    public double getDistance( double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude )
    {
        double firstRadLat = rad( firstLatitude );
        double firstRadLng = rad( firstLongitude );
        double secondRadLat = rad( secondLatitude );
        double secondRadLng = rad( secondLongitude );

        double a = firstRadLat - secondRadLat;
        double b = firstRadLng - secondRadLng;
        double cal = 2 * Math.asin( Math.sqrt( Math.pow( Math.sin( a / 2 ), 2 ) + Math.cos( firstRadLat )
                       * Math.cos( secondRadLat ) * Math.pow( Math.sin( b / 2 ), 2 ) ) ) * EarthRadius;

        double result = Math.round( cal * 10000d ) / 10000d;
        return result;
    }
}


