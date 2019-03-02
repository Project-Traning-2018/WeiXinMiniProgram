package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetFileUrl;
import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Conversion.InfoMd5;
import com.activityhelper.Enums.FileStatusEnum;
import com.activityhelper.Enums.WxInfoStausEnum;
import com.activityhelper.Pojo.FileUpload;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.WxInfo;
import com.activityhelper.Service.FileUploadService;
import com.activityhelper.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author byene
 * @date 2019/2/20 8:51 AM
 */
@Service
@Slf4j
public class FileUploadServiceimpl implements FileUploadService {

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Autowired
    InfoMd5 infoMd5;

    @Autowired
    GetFileUrl getFileUrl;

    @Override
    @Transactional
    public String  Fileupload(FileUpload fileUpload) {

        String userKey = fileUpload.getUserIdMd5();

        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return "FileUpload Error";

        MultipartFile uploadfile = fileUpload.getFile();

        /*获取用户信息*/
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        String userid = Wxresult.getOpenid();

        Date now = new Date();
        String snow = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(now);

        String name = infoMd5.String2Md5( userid + snow );

        String url = getFileUrl.GetUrl( uploadfile, name );

        return url;

    }
}
