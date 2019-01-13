package com.byene.Service;

import com.byene.Dao.UserSuggestion;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/8 8:06 PM
 */
public interface UserSuggestionService {

    /*保存用户建议*/
    void save( UserSuggestion userSuggestion );

    /*根据用户Id删除用户建议信息*/
    void deleteById( String userId );

    void delete( Integer suggestionId );

    UserSuggestion FindOneById( String userId );

    List< UserSuggestion > FindAll();

    List< UserSuggestion > FindAllbyPage( Integer Page, Integer Size );
}
