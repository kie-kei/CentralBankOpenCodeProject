package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BICDirectoryEntryResponseDTO {
    private UUID uuid;
    private String BIC;
    private String changeType;
    private ParticipantInfoResponseDTO participantInfo;
    private List<AccountsResponseDTO> accounts;
    private List<SWBICSResponseDTO> swbics;

}
