package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.BICDirectoryEntry;

@Mapper(componentModel = "spring")
public interface BICDirectoryEntryMapper {
    BICDirectoryEntryEntity toBICDirectoryEntryEntity(BICDirectoryEntry bicDirectoryEntry);

    BICDirectoryEntry toBICDirectoryEntry(BICDirectoryEntryEntity bicDirectoryEntryEntity);
}
