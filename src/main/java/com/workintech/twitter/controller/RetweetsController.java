package com.workintech.twitter.controller;

import com.workintech.twitter.service.RetweetsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/retweet")
public class RetweetsController {

    private final RetweetsService retweetsService;

    public RetweetsController(RetweetsService retweetsService) {
        this.retweetsService = retweetsService;
    }

    // POST /retweet
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void retweet(@RequestParam Long tweetId, Principal principal) {
        retweetsService.retweet(principal, tweetId);
    }

    // DELETE /retweet/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRetweet(@PathVariable Long id, Principal principal) {
        retweetsService.deleteRetweet(principal, id);
    }
}
