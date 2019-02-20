package com.activityhelper.Service;

import com.activityhelper.Dao.UserInfo;
import com.activityhelper.Pojo.ResultVO;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/11 4:34 AM
 */
public interface UserInfoService {

    /*保存用户信息*/
    void save( UserInfo userInfo );

    /*删除用户信息*/
    void delete( String userId );

    /*根据用户ID查询用户*/
    UserInfo FindOneById( String userId );

    /*查询所有用户信息*/
    List<UserInfo> FindAll( );

    /*分页查询所有用户信息*/
    List< UserInfo > FindAllbyPage( Integer Page, Integer Size );


    /*后台查询用户信息列表*/
    ResultVO BackUserInfoFindAll( );

    /*后台搜寻用户信息*/
    ResultVO BackUserInfoSearch( String str );
}
