package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BICDirectoryEntryCreateRequestDTO {
    private UUID rootId;
    private String BIC;
    private String changeType;
    private ParticipantInfoCreateRequestDTO participantInfo;
    private List<AccountsCreateRequestDTO> accounts;
    private List<SWBICSCreateRequestDTO> swbics;
}
