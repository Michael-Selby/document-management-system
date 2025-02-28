package com.AnnualProject.February.mapper;


import com.AnnualProject.February.dto.UserDto;
import com.AnnualProject.February.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
    User toUpdate(UserDto UserDto, @MappingTarget User user);
}
