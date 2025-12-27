package com.workintech.twitter.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentsRequestDto(
        @NotBlank String text
) {}
