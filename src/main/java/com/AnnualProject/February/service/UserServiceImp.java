package com.AnnualProject.February.service;

import com.AnnualProject.February.dto.UserDto;
import com.AnnualProject.February.mapper.UserMapper;
import com.AnnualProject.February.model.Role;
import com.AnnualProject.February.model.User;
import com.AnnualProject.February.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileStorageService fileStorageService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createAccount(UserDto userDto, MultipartFile file) {
        String filename = fileStorageService.storeFile(file);
        User user = userMapper.toEntity(userDto);
        user.setFilename(filename);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password here
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return user;
    }

    @Override
    public User editDocument(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            userMapper.toUpdate(userDto, existingUser);
            userRepository.save(existingUser);
            return existingUser;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User uploadDocument(UserDto userDto, MultipartFile file) {
        String filename = fileStorageService.storeFile(file);
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFilename(filename);
            userRepository.save(existingUser);
            return existingUser;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User deleteDocument(String filename) {
        try {
            fileStorageService.deleteFile(filename);
            return null; // or return some confirmation
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete document", ex);
        }
    }
}
