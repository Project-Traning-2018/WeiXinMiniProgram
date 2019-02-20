package com.activityhelper.Service;

import com.activityhelper.Pojo.FileUpload;
import com.activityhelper.Pojo.ResultVO;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * @author byene
 * @date 2019/2/20 8:48 AM
 */
public interface FileUploadService {

    /*上传单个文件*/
    String Fileupload( FileUpload fileUpload );

}
