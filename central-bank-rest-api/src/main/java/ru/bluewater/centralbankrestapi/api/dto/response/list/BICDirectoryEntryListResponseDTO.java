package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BICDirectoryEntryListResponseDTO {
    private List<BICDirectoryEntryGetResponseDTO> bicDirectoryEntryGetResponseDTOS;
    public BICDirectoryEntryListResponseDTO(){}
    public BICDirectoryEntryListResponseDTO(
            List<BICDirectoryEntryGetResponseDTO> bicDirectoryEntryGetResponseDTOS
    ){
        this.bicDirectoryEntryGetResponseDTOS = bicDirectoryEntryGetResponseDTOS;
    }
}
