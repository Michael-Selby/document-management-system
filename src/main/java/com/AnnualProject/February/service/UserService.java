package com.AnnualProject.February.service;

import com.AnnualProject.February.dto.UserDto;
import com.AnnualProject.February.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User createAccount(UserDto userDto, MultipartFile file);
    User editDocument(UserDto userDto);
    User uploadDocument(UserDto userDto, MultipartFile file);
    User deleteDocument(String filename);
}
