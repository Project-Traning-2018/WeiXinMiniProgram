package com.byene.Repository;

import com.byene.Dao.UserSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author byene
 * @date 2019/1/8 8:05 PM
 */
public interface UserSuggestionRepository extends JpaRepository<UserSuggestion, Integer> {

    UserSuggestion getUserSuggestionByUserIdIn( String userId );

}
