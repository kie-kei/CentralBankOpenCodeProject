package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.ED807;

@Mapper(componentModel = "spring")
public interface ED807Mapper {
    RootEntity toRootEntity(ED807 ed807);
    ED807 toED807(RootEntity rootEntity);
}
