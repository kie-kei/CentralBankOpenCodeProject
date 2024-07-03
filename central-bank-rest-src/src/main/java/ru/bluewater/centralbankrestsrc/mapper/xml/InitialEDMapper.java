package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.InitialEDEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.InitialED;

@Mapper(componentModel = "spring")
public interface InitialEDMapper {
    InitialED toInitialED(InitialEDEntity initialEDEntity);

    InitialEDEntity toInitialEDEntity(InitialED initialED);
}
