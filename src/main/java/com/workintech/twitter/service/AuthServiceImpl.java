package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.LoginRequestDto;
import com.workintech.twitter.dto.request.RegisterRequestDto;
import com.workintech.twitter.dto.response.RegisterResponseDto;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exception.ConflictException;
import com.workintech.twitter.repository.UsersRepository;
import com.workintech.twitter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponseDto register(RegisterRequestDto dto) {
        if (usersRepository.existsByEmail(dto.email())) {
            throw new ConflictException("Email already in use");
        }
        if (usersRepository.existsByUsername(dto.username())) {
            throw new ConflictException("Username already in use");
        }

        Users user = new Users();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        Users saved = usersRepository.save(user);
        return new RegisterResponseDto(saved.getId(), saved.getUsername(), saved.getEmail());
    }

    @Override
    public Authentication loginAndReturnAuthentication(LoginRequestDto dto) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
    }
}
