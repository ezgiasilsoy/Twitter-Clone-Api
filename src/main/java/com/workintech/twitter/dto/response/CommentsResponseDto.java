package com.workintech.twitter.dto.response;

import jakarta.validation.constraints.NotBlank;

public record CommentsResponseDto(
        String text,
        Long tweetId
) {
}
