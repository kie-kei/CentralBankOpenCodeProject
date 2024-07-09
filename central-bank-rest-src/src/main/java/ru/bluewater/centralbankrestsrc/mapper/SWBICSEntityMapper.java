package ru.bluewater.centralbankrestsrc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.SWBICSRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.SWBICSUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.SWBICSCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.SWBICSListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.SWBICSUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.dto.xml.SWBICS;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SWBICSEntityMapper {
    SWBICSEntity toSWBICSEntity(SWBICS swbics);
    SWBICS toSWBICS(SWBICSEntity swbicsEntity);
    SWBICSResponseDTO toSWBICSResponseDTO(SWBICSEntity swbicsEntity);

    SWBICSCreateResponseDTO toCreateResponse(SWBICSEntity entity);
    SWBICSUpdateResponseDTO toUpdateResponse(SWBICSEntity entity);
    SWBICSGetResponseDTO toGetResponse(SWBICSEntity entity);
    List<SWBICSGetResponseDTO> toList(List<SWBICSEntity> swbicsEntityList);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(SWBICSUpdateRequestDTO requestDTO, @MappingTarget SWBICSEntity entity);
    @Mapping(target = "uuid", ignore = true)
    SWBICSEntity toEntity(SWBICSRequestDTO requestDTO);
    SWBICSEntity fromCreateRequestToEntity(SWBICSCreateRequestDTO requestDTO);
}
