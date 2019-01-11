package com.byene.Pojo.Page;

import lombok.Data;

/**
 * @author byene
 * @date 2019/1/9 10:50 AM
 */

@Data
public class BackPage2Back {

    //页面
    private Integer page;

    //每页的大小
    private Integer size;

    public BackPage2Back() {
    }

    public BackPage2Back(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
