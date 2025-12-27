package com.workintech.twitter.service;

import com.workintech.twitter.entity.Retweets;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exception.RetweetNotFoundException;
import com.workintech.twitter.exception.TweetNotFoundException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.RetweetsRepository;
import com.workintech.twitter.repository.TweetsRepository;
import com.workintech.twitter.repository.UsersRepository;
import com.workintech.twitter.service.RetweetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class RetweetsServiceImpl implements RetweetsService {

    private final RetweetsRepository retweetsRepository;
    private final UsersRepository usersRepository;
    private final TweetsRepository tweetsRepository;

    private Users getCurrentUser(Principal principal) {
        String email = principal.getName();
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public void retweet(Principal principal, Long tweetId) {
        Users user = getCurrentUser(principal);

        if (retweetsRepository.existsByUsers_IdAndTweets_Id(user.getId(), tweetId)) return;

        Tweets tweet = tweetsRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found: " + tweetId));

        Retweets retweet = new Retweets();
        retweet.setUsers(user);
        retweet.setTweets(tweet);

        retweetsRepository.save(retweet);
    }

    @Override
    public void deleteRetweet(Principal principal, Long retweetId) {
        Users user = getCurrentUser(principal);

        Retweets retweet = retweetsRepository.findByIdAndUsers_Id(retweetId, user.getId())
                .orElseThrow(() -> new RetweetNotFoundException("Retweet not found or you are not allowed."));

        retweetsRepository.delete(retweet);
    }
}
