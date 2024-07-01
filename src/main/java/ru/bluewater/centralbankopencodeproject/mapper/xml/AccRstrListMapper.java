package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.AccRstrListEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.AccRstrList;

@Mapper(componentModel = "spring")
public interface AccRstrListMapper {
    AccRstrListEntity toAccRstrListEntity(AccRstrList accRstrList);
    AccRstrList toAccRstrList(AccRstrListEntity accRstrListEntity);
}
