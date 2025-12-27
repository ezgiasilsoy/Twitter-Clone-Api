package com.workintech.twitter.service;

import java.security.Principal;

public interface LikesService {
    void likeTweet(Principal principal, Long tweetId);
    void dislikeTweet(Principal principal, Long tweetId);
}
