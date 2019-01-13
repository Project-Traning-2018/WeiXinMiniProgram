package com.byene.RedisControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author byene
 * @date 2019/1/8 9:35 PM
 */
@Component
public class RedisClean {

    @Autowired
    StringRedisTemplate StrRedis;

    @Scheduled( fixedRate = 86400000 )
    public void Clean( )
    {
        Set< String > RedisKey = StrRedis.keys( "*" );
        for( String key: RedisKey )
        {
            if( StrRedis.getExpire( key, TimeUnit.SECONDS ) > 0  ) continue;
            StrRedis.delete( key );
        }
    }
}
