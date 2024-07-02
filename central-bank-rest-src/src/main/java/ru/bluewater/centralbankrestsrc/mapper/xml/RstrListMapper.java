package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.RstrList;

@Mapper(componentModel = "spring")
public interface RstrListMapper {
    RstrListEntity toRstrListEntity(RstrList rstrList);
    RstrList toRstrList(RstrListEntity rstrListEntity);
}
