package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.request.AccountsRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.SWBICSRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BicDirectoryEntryFullUpdateRequestDTO {
    private UUID uuid;
    private String BIC;
    private String changeType;
    private ParticipantInfoRequestDTO participantInfo;
    private List<AccountsRequestDTO> accounts;
    private List<SWBICSRequestDTO> swbics;
}
