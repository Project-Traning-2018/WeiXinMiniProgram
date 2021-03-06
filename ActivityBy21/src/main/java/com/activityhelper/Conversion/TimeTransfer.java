package com.activityhelper.Conversion;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author byene
 * @date 2019/2/10 6:09 AM
 */

@Component
public class TimeTransfer {

    private static DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    public Timestamp String2Timestamp(String Time )
    {
        return Timestamp.valueOf( Time );
    }

    public String Timestamp2String( Timestamp Time )
    {
        String timeStr = df.format( Time );
        return timeStr;
    }

}
