package com.activityhelper.FrontController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.UserSuggestion2Back;
import com.activityhelper.Service.impl.UserSuggestionServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/13 7:44 PM
 */
@RestController
@RequestMapping( "/miniapp" )
@Slf4j
public class SuggestionController {

    @Autowired
    UserSuggestionServiceimpl userSuggestionService;

    /*获取用户建议*/
    @PostMapping( "/suggestion/usersuggestion" )
    public ResultVO UserSuggestion(@RequestBody UserSuggestion2Back userSuggestion2Back )
    {
        return userSuggestionService.SubmitUserSuggestion( userSuggestion2Back );
    }
}
