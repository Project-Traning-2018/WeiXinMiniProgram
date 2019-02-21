package com.activityhelper.Service.impl;

import com.activityhelper.Conversion.GetResultVOInfo;
import com.activityhelper.Dao.UserSuggestion;
import com.activityhelper.Enums.BackInfoStausEnum;
import com.activityhelper.Enums.WxInfoStausEnum;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Pojo.UserSuggestion2Back;
import com.activityhelper.Pojo.WxInfo;
import com.activityhelper.Repository.UserSuggestionRepository;
import com.activityhelper.Service.UserSuggestionService;
import com.activityhelper.Utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author byene
 * @date 2019/2/13 7:22 PM
 */

@Service
@Slf4j
public class UserSuggestionServiceimpl implements UserSuggestionService {

    @Autowired
    UserSuggestionRepository userSuggestionRepository;

    @Autowired
    StringRedisTemplate strRedis;

    @Autowired
    GetResultVOInfo getResultVOInfo;

    @Override
    @Transactional
    public void save(UserSuggestion userSuggestion)
    {

        userSuggestionRepository.save( userSuggestion );
    }

    @Override
    @Transactional
    public void deleteById(String userId)
    {

        UserSuggestion userSuggestion = userSuggestionRepository.getUserSuggestionByUserIdIn( userId );
        if( userSuggestion == null ) return;
        userSuggestionRepository.delete( userSuggestion );
    }

    @Override
    @Transactional
    public void delete(Integer suggestionId)
    {
        UserSuggestion userSuggestion = userSuggestionRepository.getUserSuggestionBySuggestionId( suggestionId );
        userSuggestionRepository.delete( userSuggestion );
    }

    @Override
    @Transactional
    public UserSuggestion FindOneById(String userId) {

        return userSuggestionRepository.getUserSuggestionByUserIdIn( userId );
    }

    @Override
    @Transactional
    public List<UserSuggestion> FindAll()
    {
        return userSuggestionRepository.findAll();
    }

    @Override
    @Transactional
    public List<UserSuggestion> FindAllbyPage(Integer Page, Integer Size)
    {

        Pageable pageable = PageRequest.of( Page, Size );

        Page< UserSuggestion > userSuggestionPage = userSuggestionRepository.findAll( pageable );
        List< UserSuggestion > userSuggestionList = userSuggestionPage.getContent();

        return userSuggestionList;
    }

    @Override
    @Transactional
    public ResultVO SubmitUserSuggestion(UserSuggestion2Back userSuggestion2Back) {

        log.info("[userKey]              [{}]", userSuggestion2Back.getUserIdMd5()  );
        log.info("[userSuggestion]       [{}]", userSuggestion2Back.getSuggestion() );

        /*在redis中查询userKey*/
        String userKey = userSuggestion2Back.getUserIdMd5();

        /*userKey已过期,返回身份过期信息*/
        if( strRedis.opsForValue().get( userKey ) == null )
            return getResultVOInfo.GetResultVO( WxInfoStausEnum.WX_ERROR.getCode(), WxInfoStausEnum.WX_ERROR.getMessage(), null );

        /*userKey未过期,将openid和建议存储到mysql中*/
        UserSuggestion userSuggestion = new UserSuggestion();
        WxInfo Wxresult = JsonUtils.jsonToPojo( strRedis.opsForValue().get( userKey ), WxInfo.class );

        userSuggestion.setUserId( Wxresult.getOpenid() );
        userSuggestion.setSuggestionContent( userSuggestion2Back.getSuggestion() );

        userSuggestionRepository.save( userSuggestion );

        /*返回用户建议提交成功*/
        return getResultVOInfo.GetResultVO( WxInfoStausEnum.SUGGESTION_SUCCESS.getCode(), WxInfoStausEnum.SUGGESTION_SUCCESS.getMessage(), null );
    }

    @Override
    public ResultVO BackSuggersionFindall() {
        List< UserSuggestion > userSuggestionList = FindAll();
        return getResultVOInfo.GetResultVO(BackInfoStausEnum.BACK_SUGGESTION_SUCCESS.getCode(), BackInfoStausEnum.BACK_SUGGESTION_SUCCESS.getMessage(),userSuggestionList);
    }

    @Override
    public ResultVO BackSuggersionGetOne(String userId) {

        UserSuggestion userSuggestion = FindOneById( userId );
        return getResultVOInfo.GetResultVO( BackInfoStausEnum.BACK_SUGGESTION_GETONE_SUCCESS.getCode(), BackInfoStausEnum.BACK_SUGGESTION_GETONE_SUCCESS.getMessage(), userSuggestion );

    }

    @Override
    public ResultVO BackSuggersionDelete( Integer suggestionId ) {

        delete( suggestionId );

        return getResultVOInfo.GetResultVO( BackInfoStausEnum.BACK_SUGGESTION_DELETE_SUCCESS.getCode(), BackInfoStausEnum.BACK_SUGGESTION_DELETE_SUCCESS.getMessage(), null );
    }

    @Override
    public ResultVO BackSuggestionSearch(String str) {
        List< UserSuggestion > userSuggestionList = userSuggestionRepository.findAllBySuggestionContentContaining( str );
        return getResultVOInfo.GetResultVO( BackInfoStausEnum.BACK_SUGGESTION_SEARCH_SUCCESS.getCode(), BackInfoStausEnum.BACK_SUGGESTION_SEARCH_SUCCESS.getMessage(), userSuggestionList );
    }

    @Override
    public ResultVO FindOneBysuggestionId(Integer suggestionid) {

        UserSuggestion userSuggestion = userSuggestionRepository.getUserSuggestionBySuggestionId( suggestionid );
        return getResultVOInfo.GetResultVO( BackInfoStausEnum.BACK_SUGGESTION_GETONE_SUCCESS.getCode(), BackInfoStausEnum.BACK_SUGGESTION_GETONE_SUCCESS.getMessage(), userSuggestion );
    }
}
