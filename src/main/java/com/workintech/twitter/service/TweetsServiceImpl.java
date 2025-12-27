package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.TweetsRequestDto;
import com.workintech.twitter.dto.response.TweetsResponseDto;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exception.ForbiddenException;
import com.workintech.twitter.exception.TweetNotFoundException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.mapper.TweetsMapper;
import com.workintech.twitter.repository.TweetsRepository;
import com.workintech.twitter.repository.UsersRepository;
import com.workintech.twitter.service.TweetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetsServiceImpl implements TweetsService {

    private final TweetsRepository tweetsRepository;
    private final UsersRepository usersRepository;
    private final TweetsMapper tweetsMapper;

    private Users getCurrentUser(Principal principal) {
        String email = principal.getName();
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public TweetsResponseDto createTweet(Principal principal, TweetsRequestDto dto) {
        Users user = getCurrentUser(principal);

        Tweets tweet = tweetsMapper.toEntity(dto);
        tweet.setUsers(user);
        tweet.setCreationDate(LocalDateTime.now());

        Tweets saved = tweetsRepository.save(tweet);
        return tweetsMapper.toResponse(saved);
    }

    @Override
    public List<TweetsResponseDto> findMyTweets(Principal principal) {
        Users user = getCurrentUser(principal);

        return tweetsRepository.findByUsers_Id(user.getId())
                .stream()
                .map(tweetsMapper::toResponse)
                .toList();
    }

    @Override
    public TweetsResponseDto findTweetById(Long tweetId) {
        Tweets tweet = tweetsRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found: " + tweetId));
        return tweetsMapper.toResponse(tweet);
    }

    @Override
    public TweetsResponseDto updateTweet(Long tweetId, Principal principal, TweetsRequestDto dto) {
        Users user = getCurrentUser(principal);

        Tweets tweet = tweetsRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found: " + tweetId));

        if (!tweet.getUsers().getId().equals(user.getId())) {
            throw new ForbiddenException("You are not allowed to update this tweet.");
        }

        tweetsMapper.updateEntityFromDto(dto, tweet);
        Tweets saved = tweetsRepository.save(tweet);
        return tweetsMapper.toResponse(saved);
    }

    @Override
    public void deleteTweet(Long tweetId, Principal principal) {
        Users user = getCurrentUser(principal);

        Tweets tweet = tweetsRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found: " + tweetId));

        if (!tweet.getUsers().getId().equals(user.getId())) {
            throw new ForbiddenException("You are not allowed to delete this tweet.");
        }

        tweetsRepository.delete(tweet);
    }
}
