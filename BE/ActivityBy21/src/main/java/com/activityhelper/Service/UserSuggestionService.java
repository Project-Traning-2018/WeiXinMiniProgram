package com.activityhelper.Service;

import com.activityhelper.Dao.UserSuggestion;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.UserSuggestion2Back;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/13 7:16 PM
 */
public interface UserSuggestionService {

    /*保存用户建议*/
    void save( UserSuggestion userSuggestion );

    /*根据用户Id删除用户建议信息*/
    void deleteById( String userId );

    /*根据建议Id删除用户建议*/
    void delete( Integer suggestionId );

    /*根据用户Id查询一个建议*/
    UserSuggestion FindOneById( String userId );

    /*查询所有用户建议*/
    List< UserSuggestion > FindAll();

    /*分页查询所有用户建议*/
    List< UserSuggestion > FindAllbyPage( Integer Page, Integer Size );

    /*前端提交用户信息*/
    ResultVO SubmitUserSuggestion( UserSuggestion2Back userSuggestion2Back );

    /*后端获取建议列表*/
    ResultVO BackSuggersionFindall();

    /*后端查询一条建议列表*/
    ResultVO BackSuggersionGetOne( String userId );

    /*后端删除建议*/
    ResultVO BackSuggersionDelete( Integer suggestionId );

    /*后端搜寻建议*/
    ResultVO BackSuggestionSearch( String str );

    ResultVO FindOneBysuggestionId( Integer suggestionid );

}
