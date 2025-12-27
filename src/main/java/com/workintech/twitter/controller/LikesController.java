package com.workintech.twitter.controller;

import com.workintech.twitter.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class LikesController {

    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    // POST /like
    @PostMapping("/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void likeTweet(@RequestParam Long tweetId, Principal principal) {
        likesService.likeTweet(principal, tweetId);
    }

    // POST /dislike
    @DeleteMapping("/dislike")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislikeTweet(@RequestParam Long tweetId, Principal principal) {
        likesService.dislikeTweet(principal, tweetId);
    }
}
