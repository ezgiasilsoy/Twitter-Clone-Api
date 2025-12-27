package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.LoginRequestDto;
import com.workintech.twitter.dto.request.RegisterRequestDto;
import com.workintech.twitter.dto.response.RegisterResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto dto);

    Authentication loginAndReturnAuthentication(LoginRequestDto dto);
}
