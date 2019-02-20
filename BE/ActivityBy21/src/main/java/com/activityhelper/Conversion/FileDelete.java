package com.activityhelper.Conversion;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

/**
 * @author byene
 * @date 2019/2/16 8:38 PM
 */
@Component
public class FileDelete {

    public void Delete( String name )
    {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());

        String accessKey = "sLGOx5f7gtIvR-Ti_opj9eBHvqGbqQdBZ1SD4shw";
        String secretKey = "iTouIQDnGcPDiXHuUpq90DqSjhym15lNmbFhAAAu";
        String bucket = "miniappimage";
        String key = name;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
