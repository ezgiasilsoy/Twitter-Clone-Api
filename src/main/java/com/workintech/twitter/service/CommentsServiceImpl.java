package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.CommentsRequestDto;
import com.workintech.twitter.dto.response.CommentsResponseDto;
import com.workintech.twitter.entity.Comments;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exception.CommentNotFoundException;
import com.workintech.twitter.exception.TweetNotFoundException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.mapper.CommentsMapper;
import com.workintech.twitter.repository.CommentsRepository;
import com.workintech.twitter.repository.TweetsRepository;
import com.workintech.twitter.repository.UsersRepository;
import com.workintech.twitter.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final TweetsRepository tweetsRepository;
    private final UsersRepository usersRepository;
    private final CommentsMapper commentsMapper;

    private Users getCurrentUser(Principal principal) {
        String email = principal.getName();
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public CommentsResponseDto createComment(Principal principal, Long tweetId, CommentsRequestDto dto) {
        Users user = getCurrentUser(principal);

        Tweets tweet = tweetsRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found: " + tweetId));

        Comments comment = commentsMapper.toEntity(dto);
        comment.setUsers(user);
        comment.setTweets(tweet);

        Comments saved = commentsRepository.save(comment);
        return commentsMapper.toResponse(saved);
    }

    @Override
    public CommentsResponseDto updateComment(Principal principal, Long commentId, CommentsRequestDto dto) {
        Users user = getCurrentUser(principal);

        Comments comment = commentsRepository.findByIdAndUsers_Id(commentId, user.getId())
                .orElseThrow(() -> new CommentNotFoundException("Comment not found or you are not allowed."));

        commentsMapper.updateEntityFromDto(dto, comment);

        Comments saved = commentsRepository.save(comment);
        return commentsMapper.toResponse(saved);
    }

    @Override
    public void deleteComment(Principal principal, Long commentId) {
        Users user = getCurrentUser(principal);

        Comments comment = commentsRepository.findByIdAndUsers_Id(commentId, user.getId())
                .orElseThrow(() -> new CommentNotFoundException("Comment not found or you are not allowed."));

        commentsRepository.delete(comment);
    }
}
