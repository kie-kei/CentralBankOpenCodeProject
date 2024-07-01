package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.RstrListEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.RstrList;

@Mapper(componentModel = "spring")
public interface RstrListMapper {
    RstrListEntity toRstrListEntity(RstrList rstrList);
    RstrList toRstrList(RstrListEntity rstrListEntity);
}
