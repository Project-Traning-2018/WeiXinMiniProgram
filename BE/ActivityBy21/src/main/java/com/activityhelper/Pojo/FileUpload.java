package com.activityhelper.Pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author byene
 * @date 2019/2/20 8:47 AM
 */
@Data
public class FileUpload {

    /*前端用户登录凭证对应值*/
    private String userIdMd5;

    private MultipartFile file;

    public FileUpload() {
    }

    public FileUpload(String userIdMd5, MultipartFile file) {
        this.userIdMd5 = userIdMd5;
        this.file = file;
    }
}
