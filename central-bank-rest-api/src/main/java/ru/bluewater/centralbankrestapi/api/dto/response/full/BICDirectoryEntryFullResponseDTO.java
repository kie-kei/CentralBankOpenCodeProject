package ru.bluewater.centralbankrestapi.api.dto.response.full;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;

import java.util.List;

@Getter
@Setter
public class BICDirectoryEntryFullResponseDTO {
    private List<BICDirectoryEntryResponseDTO> bicDirectoryEntryList;
    public BICDirectoryEntryFullResponseDTO(){}
    public BICDirectoryEntryFullResponseDTO(
            List<BICDirectoryEntryResponseDTO> bicDirectoryEntryList
    ){
        this.bicDirectoryEntryList = bicDirectoryEntryList;
    }
}
