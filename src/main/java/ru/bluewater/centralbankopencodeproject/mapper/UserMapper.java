package ru.bluewater.centralbankopencodeproject.mapper;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankopencodeproject.entity.UserEntity;
@Mapper
public interface UserMapper {
    UserEntity toEntity(RegistrationRequestDTO request);
}
