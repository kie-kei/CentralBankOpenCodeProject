package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.PartInfoEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.PartInfo;

@Mapper(componentModel = "spring")
public interface PartInfoMapper {
    PartInfoEntity toPartInfoEntity(PartInfo partInfo);
    PartInfo toPartInfo(PartInfoEntity partInfoEntity);
}
