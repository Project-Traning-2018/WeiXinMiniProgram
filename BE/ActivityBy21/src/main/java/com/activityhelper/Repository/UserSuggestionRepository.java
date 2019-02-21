package com.activityhelper.Repository;

import com.activityhelper.Dao.UserSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author byene
 * @date 2019/2/13 7:14 PM
 */
public interface UserSuggestionRepository extends JpaRepository< UserSuggestion, Integer > {

    /*根据用户ID获取用户建议*/
    UserSuggestion getUserSuggestionByUserIdIn( String userId );

    List< UserSuggestion > findAllBySuggestionContentContaining(String str );

    UserSuggestion getUserSuggestionBySuggestionId( Integer suggestionId );
}
