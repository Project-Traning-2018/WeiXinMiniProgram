package com.byene.Conversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author byene
 * @date 2019/1/13 2:50 PM
 */
@Component
@Slf4j
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

    /*
    private static double rad(double d) {

        return d * Math.PI / 180.00; //角度转换成弧度

    }

    public  double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {

        double Lat1 = rad(latitude1); // 纬度

        double Lat2 = rad(latitude2);

        double a = Lat1 - Lat2;//两点纬度之差

        double b = rad(longitude1) - rad(longitude2); //经度之差

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

        s = s * 6378137.0;//弧长乘地球半径（半径为米）

        s = Math.round(s * 10000d) / 10000d;//精确距离的数值

        return s;
        }
        */
}


