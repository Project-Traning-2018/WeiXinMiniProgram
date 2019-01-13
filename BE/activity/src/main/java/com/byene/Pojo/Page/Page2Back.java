package com.byene.Pojo.Page;

import lombok.Data;

/**
 * 前端后端发送页面的实体类
 * @author byene
 * @date 2019/1/9 9:21 AM
 */
@Data
public class Page2Back {

    /*前端用户登录凭证对应值*/
    private  String userIdMd5;

    //页面
    private Integer page;

    //每页的大小
    private Integer size;

    public Page2Back() {
    }

    public Page2Back(String userIdMd5, Integer page, Integer size) {
        this.userIdMd5 = userIdMd5;
        this.page = page;
        this.size = size;
    }
}
