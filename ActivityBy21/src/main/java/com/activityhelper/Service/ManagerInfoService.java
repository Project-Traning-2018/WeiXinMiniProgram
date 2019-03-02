package com.activityhelper.Service;

import com.activityhelper.Dao.ManagerInfo;
import com.activityhelper.Pojo.ManagerInfo2Back;
import com.activityhelper.Pojo.ResultVO;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/13 7:54 PM
 */
public interface ManagerInfoService {

    /*保存管理员信息*/
    void save( ManagerInfo managerInfo );

    /*删除管理员信息*/
    void delete( String managerLoginno );

    /*更新管理员信息*/
    void update( ManagerInfo managerInfo );

    /*查询所有管理员信息*/
    List< ManagerInfo > findall( String managerLoginno );

    /*根据管理员账号查询管理员信息*/
    ManagerInfo findbymanagerId( String managerLoginno );

    /*添加管理员账号*/
    ResultVO managerSave(ManagerInfo2Back managerInfo2Back );

    /*更新管理员账号信息*/
    ResultVO managerUpdate( ManagerInfo2Back managerInfo2Back );

    /*获取管理员账号信息列表*/
    ResultVO managerInfoList( String  managerLoginno );

    /*删除管理员账号信息*/
    ResultVO managerInfoDelete( String managerLoginno );

    /*判断是否为超级管理员*/
    ResultVO supermanagerCheck( String managerLoginno );

    /*根据字符串搜寻管理员*/
    ResultVO SearchmanagerList( String str );
}
