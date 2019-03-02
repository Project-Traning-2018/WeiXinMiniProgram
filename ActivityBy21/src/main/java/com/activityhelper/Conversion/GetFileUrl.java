package com.activityhelper.Conversion;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author byene
 * @date 2019/2/15 8:39 PM
 */
@Component
public class GetFileUrl {

    @Autowired
    FileDelete fileDelete;

    public String GetUrl( MultipartFile file, String name )
    {
        String FileName = file.getOriginalFilename();  // 文件名
        String SuffixName = FileName.substring(FileName.lastIndexOf("."));  // 后缀名
        String FilePath = "/Users/byene/Downloads";

        String fileName1 = name;

        fileDelete.Delete( name );

        File newFile = new File(FilePath, fileName1 + SuffixName);

        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration cfg = new Configuration(Zone.zone2());

        UploadManager uploadManager = new UploadManager(cfg);

        //AccessKey的值
        String accessKey = "sLGOx5f7gtIvR-Ti_opj9eBHvqGbqQdBZ1SD4shw";

        //SecretKey的值
        String secretKey = "iTouIQDnGcPDiXHuUpq90DqSjhym15lNmbFhAAAu";

        //存储空间名
        String bucket = "miniappimage";

        Auth auth = Auth.create(accessKey, secretKey);

        String key = newFile.getName();

        //上传图片路径
        String localFilePath = "/Users/byene/Downloads/" + newFile.getName();

        String upToken = auth.uploadToken(bucket);

        try {


            Response response = uploadManager.put(localFilePath, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

        newFile.delete();

        return "http://pmai8fe3u.bkt.clouddn.com/" + key;
    }
}
