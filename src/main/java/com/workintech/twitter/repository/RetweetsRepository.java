package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Retweets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetweetsRepository extends JpaRepository<Retweets, Long> {
    boolean existsByUsers_IdAndTweets_Id(Long userId, Long tweetId);
    Optional<Retweets> findByIdAndUsers_Id(Long retweetId, Long userId);
}
