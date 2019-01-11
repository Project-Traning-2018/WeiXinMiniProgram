package com.byene.Pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author byene
 * @date 2019/1/11 10:54 PM
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
