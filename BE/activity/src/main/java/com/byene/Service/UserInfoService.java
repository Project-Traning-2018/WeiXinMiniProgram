package com.byene.Service;

import com.byene.Dao.UserInfo;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/8 7:47 PM
 */
public interface UserInfoService {

    /*保存用户信息*/
    void save( UserInfo userInfo );

    /*删除用户信息*/
    void delete( String userId );

    /*根据用户ID查询用户*/
    UserInfo FindOneById( String userId );

    /*查询所有用户信息*/
    public List<UserInfo> FindAll( );

    /*分页查询所有用户信息*/
    List< UserInfo > FindAllbyPage( Integer Page, Integer Size );

}
