package com.activityhelper.Dao;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author byene
 * @date 2019/2/10 5:32 AM
 */
@Entity
@Table( name = "user_suggestion" )
@Data
public class UserSuggestion {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer suggestionId;

    private String  userId;

    private String  suggestionContent;

    private Timestamp createTime;

    public UserSuggestion() {
    }

    public UserSuggestion(String userId, String suggestionContent) {
        this.userId = userId;
        this.suggestionContent = suggestionContent;
    }
}
