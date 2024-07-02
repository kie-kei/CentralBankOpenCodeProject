package ru.bluewater.centralbankrestsrc.mapper;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankrestsrc.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserEntity toEntity(RegistrationRequestDTO request);
}
