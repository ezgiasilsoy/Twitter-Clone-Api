package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.TweetsRequestDto;
import com.workintech.twitter.dto.response.TweetsResponseDto;

import java.security.Principal;
import java.util.List;

public interface TweetsService {
    TweetsResponseDto createTweet(Principal principal, TweetsRequestDto dto);
    List<TweetsResponseDto> findMyTweets(Principal principal);
    TweetsResponseDto findTweetById(Long tweetId);
    TweetsResponseDto updateTweet(Long tweetId, Principal principal, TweetsRequestDto dto);
    void deleteTweet(Long tweetId, Principal principal);
}
