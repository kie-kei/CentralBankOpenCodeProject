package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.InitialEDEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.InitialED;

@Mapper(componentModel = "spring")
public interface InitialEDMapper {
    InitialED toInitialED(InitialEDEntity initialEDEntity);
    InitialEDEntity toInitialEDEntity(InitialED initialED);
}
