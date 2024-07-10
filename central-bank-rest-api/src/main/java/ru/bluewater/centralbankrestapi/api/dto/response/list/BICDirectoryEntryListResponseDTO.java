package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;

import java.util.List;

@Getter
@Setter
public class BICDirectoryEntryListResponseDTO {
    private List<BICDirectoryEntryGetResponseDTO> bicDirectoryEntryList;

    public BICDirectoryEntryListResponseDTO(List<BICDirectoryEntryGetResponseDTO> bicDirectoryEntryList){
        this.bicDirectoryEntryList = bicDirectoryEntryList;
    }

    public BICDirectoryEntryListResponseDTO(){}
}
