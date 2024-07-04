package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.request.AccountsRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.SWBICSRequestDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BicDirectoryEntryCreateRequestDTO {
    private UUID rootId;
    private String BIC;
    private String changeType;
    private ParticipantInfoRequestDTO participantInfo;
    private List<AccountsRequestDTO> accounts;
    private List<SWBICSRequestDTO> swbics;
}
