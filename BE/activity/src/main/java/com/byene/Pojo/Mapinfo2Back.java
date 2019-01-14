package com.byene.Pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author byene
 * @date 2019/1/13 3:39 PM
 */
@Data
public class MapInfo2Back {

    /*前端用户登录凭证对应值*/
    private  String userIdMd5;

    private String activityAddress;

    private String activityAddressname;

    private BigDecimal activityLongitude;

    private BigDecimal activityLatitude;

    public MapInfo2Back() {
    }

    public MapInfo2Back(String userIdMd5, String activityAddress, String activityAddressname, BigDecimal activityLongitude, BigDecimal activityLatitude) {
        this.userIdMd5 = userIdMd5;
        this.activityAddress = activityAddress;
        this.activityAddressname = activityAddressname;
        this.activityLongitude = activityLongitude;
        this.activityLatitude = activityLatitude;
    }
}
