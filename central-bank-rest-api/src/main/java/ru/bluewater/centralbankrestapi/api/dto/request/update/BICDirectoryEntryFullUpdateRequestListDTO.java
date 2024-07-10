package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BICDirectoryEntryFullUpdateRequestListDTO {
    List<BicDirectoryEntryFullUpdateRequestDTO> bicDirectoryEntryFullUpdateRequestDTOList;
}
