package com.byene.Pojo.Suggestion;

import lombok.Data;

/**
 * @author byene
 * @date 2019/1/9 11:31 AM
 */
@Data
public class SuggestionDelete2Back {

    private Integer suggestionId;

    public SuggestionDelete2Back() {
    }

    public SuggestionDelete2Back(Integer suggestionId) {
        this.suggestionId = suggestionId;
    }
}
