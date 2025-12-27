package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record TweetsRequestDto(
        @NotBlank
        @JsonProperty("text")
        String text
) {}
