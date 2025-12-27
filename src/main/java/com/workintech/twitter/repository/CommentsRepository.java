package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Optional<Comments> findByIdAndUsers_Id(Long commentId, Long userId);
}
