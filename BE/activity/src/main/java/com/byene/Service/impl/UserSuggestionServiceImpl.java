package com.byene.Service.impl;

import com.byene.Dao.UserSuggestion;
import com.byene.Repository.UserSuggestionRepository;
import com.byene.Service.UserSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author byene
 * @date 2019/1/8 8:09 PM
 */

@Service
public class UserSuggestionServiceImpl implements UserSuggestionService {

    @Autowired
    UserSuggestionRepository userSuggestionRepository;

    //private static SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );

    @Override
    @Transactional
    public void save(UserSuggestion userSuggestion) {
        userSuggestionRepository.save( userSuggestion );
    }

    @Override
    @Transactional
    public void deleteById(String userId) {

        UserSuggestion userSuggestion = userSuggestionRepository.getUserSuggestionByUserIdIn( userId );
        if( userSuggestion == null ) return;
        userSuggestionRepository.delete( userSuggestion );
    }

    @Override
    @Transactional
    public void delete( Integer suggestionId ) {
        userSuggestionRepository.deleteById( suggestionId );
    }

    @Override
    @Transactional
    public UserSuggestion FindOneById(String userId) {
        return userSuggestionRepository.getUserSuggestionByUserIdIn( userId );
    }

    @Override
    @Transactional
    public List<UserSuggestion> FindAll() {
        return userSuggestionRepository.findAll();
    }

    @Override
    @Transactional
    public List<UserSuggestion> FindAllbyPage( Integer Page, Integer Size ) {

        Pageable pageable = PageRequest.of( Page, Size );

        Page< UserSuggestion > userSuggestionPage = userSuggestionRepository.findAll( pageable );

        List< UserSuggestion > userSuggestionList = userSuggestionPage.getContent();

        return userSuggestionList;
    }
}
