package com.workintech.twitter.service;

import java.security.Principal;

public interface RetweetsService {
    void retweet(Principal principal, Long tweetId);
    void deleteRetweet(Principal principal, Long retweetId);
}
