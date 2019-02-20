package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/19 2:32 PM
 */

@Data
public class BackManagerInfo {

    private String managerLoginno;

    private String managerPassword;

    public BackManagerInfo() {
    }

    public BackManagerInfo(String managerLoginno, String managerPassword) {
        this.managerLoginno = managerLoginno;
        this.managerPassword = managerPassword;
    }
}
