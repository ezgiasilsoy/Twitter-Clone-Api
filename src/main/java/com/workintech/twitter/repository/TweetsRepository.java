package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetsRepository extends JpaRepository<Tweets, Long> {
    List<Tweets> findByUsers_Id(Long userId);
}
