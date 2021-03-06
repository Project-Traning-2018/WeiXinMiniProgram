package com.activityhelper.Dao;

import lombok.Data;

import javax.persistence.*;

/**
 * @author byene
 * @date 2019/2/10 5:30 AM
 */
@Entity
@Table( name = "manager_info" )
@Data
public class ManagerInfo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer managerId;

    private String managerNickname;

    private String managerLoginno;

    private String managerPassword;

    public ManagerInfo() {
    }

    public ManagerInfo(String managerNickname, String managerLoginno, String managerPassword) {
        this.managerNickname = managerNickname;
        this.managerLoginno = managerLoginno;
        this.managerPassword = managerPassword;
    }
}
