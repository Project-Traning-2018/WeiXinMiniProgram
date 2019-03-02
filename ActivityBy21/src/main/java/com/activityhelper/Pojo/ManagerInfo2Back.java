package com.activityhelper.Pojo;

import lombok.Data;

/**
 * @author byene
 * @date 2019/2/13 8:14 PM
 */
@Data
public class ManagerInfo2Back {

    private String managerNickname;

    private String managerLoginno;

    private String managerPassword;

    public ManagerInfo2Back() {
    }

    public ManagerInfo2Back(String managerNickname, String managerLoginno, String managerPassword) {
        this.managerNickname = managerNickname;
        this.managerLoginno = managerLoginno;
        this.managerPassword = managerPassword;
    }
}
