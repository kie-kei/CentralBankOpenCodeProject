package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.RootEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.ED807;

@Mapper(componentModel = "spring")
public interface ED807Mapper {
    RootEntity toRootEntity(ED807 ed807);
    ED807 toED807(RootEntity rootEntity);
}
