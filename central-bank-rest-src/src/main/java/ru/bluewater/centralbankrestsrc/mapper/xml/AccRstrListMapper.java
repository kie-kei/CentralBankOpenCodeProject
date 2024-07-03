package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.AccRstrList;

@Mapper(componentModel = "spring")
public interface AccRstrListMapper {
    AccRstrListEntity toAccRstrListEntity(AccRstrList accRstrList);

    AccRstrList toAccRstrList(AccRstrListEntity accRstrListEntity);
}
