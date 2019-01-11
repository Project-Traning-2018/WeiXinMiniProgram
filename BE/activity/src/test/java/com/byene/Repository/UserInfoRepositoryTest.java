package com.byene.Repository;

import com.byene.Dao.UserInfo;
import com.byene.Pojo.WxInfo;
import com.byene.Utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author byene
 * @date 2019/1/8 7:49 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    StringRedisTemplate StrRedis;

    @Test
    public void ResdisAdd(  )
    {
        String key = "test";

        WxInfo wxInfo = new WxInfo();
        wxInfo.setOpenid( "test" );
        wxInfo.setSession_key( "test" );
        StrRedis.opsForValue().set( key, JsonUtils.objectToJson( wxInfo ),60*100, TimeUnit.SECONDS );

        String key1 = "test1";

        WxInfo wxInfo1 = new WxInfo();
        wxInfo1.setOpenid( "test1" );
        wxInfo1.setSession_key( "test1" );
        StrRedis.opsForValue().set( key1, JsonUtils.objectToJson( wxInfo1 ),60*100, TimeUnit.SECONDS );

        String key2 = "test2";

        WxInfo wxInfo2 = new WxInfo();
        wxInfo2.setOpenid( "test2" );
        wxInfo2.setSession_key( "test2" );
        StrRedis.opsForValue().set( key2, JsonUtils.objectToJson( wxInfo2 ),60*100, TimeUnit.SECONDS );
    }
}