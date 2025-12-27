package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.mapper.UsersMapper;
import com.workintech.twitter.repository.UsersRepository;
import com.workintech.twitter.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UserService {

    private final UsersRepository userRepository;
    private final UsersMapper usersMapper;

    public UsersServiceImpl(UsersRepository userRepository, UsersMapper userMapper) {
        this.userRepository = userRepository;
        this.usersMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(usersMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı, id: " + id));
        return usersMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        Users user = usersMapper.toEntity(userRequestDto);
        Users saved = userRepository.save(user);
        return usersMapper.toResponseDto(saved);
    }

    @Override
    public UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto) {
        Users userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı, id: " + id));

        usersMapper.updateEntity(userToUpdate, userPatchRequestDto);

        Users saved = userRepository.save(userToUpdate);
        return usersMapper.toResponseDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
