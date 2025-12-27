package com.workintech.twitter.service;

import com.workintech.twitter.entity.Likes;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exception.LikeNotFoundException;
import com.workintech.twitter.exception.TweetNotFoundException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.LikesRepository;
import com.workintech.twitter.repository.TweetsRepository;
import com.workintech.twitter.repository.UsersRepository;
import com.workintech.twitter.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;
    private final UsersRepository usersRepository;
    private final TweetsRepository tweetsRepository;

    private Users getCurrentUser(Principal principal) {
        String email = principal.getName();
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public void likeTweet(Principal principal, Long tweetId) {
        Users user = getCurrentUser(principal);

        if (likesRepository.existsByUsers_IdAndTweets_Id(user.getId(), tweetId)) return;

        Tweets tweet = tweetsRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found: " + tweetId));

        Likes like = new Likes();
        like.setUsers(user);
        like.setTweets(tweet);

        likesRepository.save(like);
    }

    @Override
    public void dislikeTweet(Principal principal, Long tweetId) {
        Users user = getCurrentUser(principal);

        Likes like = likesRepository.findByUsers_IdAndTweets_Id(user.getId(), tweetId)
                .orElseThrow(() -> new LikeNotFoundException("Like not found."));

        likesRepository.delete(like);
    }
}
