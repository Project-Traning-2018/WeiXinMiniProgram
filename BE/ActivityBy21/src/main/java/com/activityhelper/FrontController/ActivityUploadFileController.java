package com.activityhelper.FrontController;

import com.activityhelper.Pojo.FileUpload;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.FileUploadService;
import com.activityhelper.Service.impl.FileUploadServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/20 9:05 AM
 */
@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class ActivityUploadFileController {

    @Autowired
    FileUploadServiceimpl fileUploadService;

    @PostMapping( "/fileupload" )
    public String Fileupload(FileUpload fileUpload)
    {
        return fileUploadService.Fileupload( fileUpload );
    }
}
