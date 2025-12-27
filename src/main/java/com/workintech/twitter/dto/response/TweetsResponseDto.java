package com.workintech.twitter.dto.response;

import java.time.LocalDateTime;

public record TweetsResponseDto(Long id, String text, LocalDateTime creationDate, Long userId) {}
