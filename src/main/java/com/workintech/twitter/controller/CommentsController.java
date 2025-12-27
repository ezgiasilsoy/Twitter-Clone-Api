package com.workintech.twitter.controller;

import com.workintech.twitter.dto.request.CommentsRequestDto;
import com.workintech.twitter.dto.response.CommentsResponseDto;
import com.workintech.twitter.service.CommentsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comment")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    // POST /comment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentsResponseDto createComment(
            @RequestParam Long tweetId,
            @Valid @RequestBody CommentsRequestDto dto,
            Principal principal
    ) {
        return commentsService.createComment(principal, tweetId, dto);
    }

    // PUT /comment/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentsResponseDto updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentsRequestDto dto,
            Principal principal
    ) {
        return commentsService.updateComment(principal, id, dto);
    }

    // DELETE /comment/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id, Principal principal) {
        commentsService.deleteComment(principal, id);
    }
}
