package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.CommentsRequestDto;
import com.workintech.twitter.dto.response.CommentsResponseDto;

import java.security.Principal;

public interface CommentsService {
    CommentsResponseDto createComment(Principal principal, Long tweetId, CommentsRequestDto dto);
    CommentsResponseDto updateComment(Principal principal, Long commentId, CommentsRequestDto dto);
    void deleteComment(Principal principal, Long commentId);
}
