package com.activityhelper.BackController;

import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.UserSuggestionServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byene
 * @date 2019/2/19 9:56 AM
 */
@RestController
@RequestMapping( "/back" )
public class UserSuggestionController {

    @Autowired
    UserSuggestionServiceimpl userSuggestionService;

    @PostMapping( "/suggestion/list" )
    public ResultVO BackSuggersionFindall()
    {
        return userSuggestionService.BackSuggersionFindall();
    }

    @PostMapping( "/suggestion/getone" )
    public ResultVO BackSuggersionGetOne(@RequestParam("userId") String userId)
    {
        return userSuggestionService.BackSuggersionGetOne( userId );
    }

    @PostMapping( "/suggestion/delete" )
    public ResultVO BackSuggersionDelete(@RequestParam("userId") String userId)
    {
        return userSuggestionService.BackSuggersionDelete( userId );
    }

    @PostMapping( "/suggestion/search" )
    public ResultVO BackSuggestionSearch(@RequestParam("str") String str)
    {
        return userSuggestionService.BackSuggestionSearch( str );
    }

}
