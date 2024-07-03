package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.PartInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.PartInfo;

@Mapper(componentModel = "spring")
public interface PartInfoMapper {
    PartInfoEntity toPartInfoEntity(PartInfo partInfo);

    PartInfo toPartInfo(PartInfoEntity partInfoEntity);
}
