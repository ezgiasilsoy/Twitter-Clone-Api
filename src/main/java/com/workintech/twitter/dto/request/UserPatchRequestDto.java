package com.workintech.twitter.dto.request;

import jakarta.validation.constraints.Email;

public record UserPatchRequestDto(
        String username,
        @Email String email
) {}
