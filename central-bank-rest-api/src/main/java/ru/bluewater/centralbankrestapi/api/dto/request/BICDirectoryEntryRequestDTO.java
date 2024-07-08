package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class BICDirectoryEntryRequestDTO {
    private String BIC;
    private String changeType;
    private ParticipantInfoResponseDTO participantInfo;
    private List<AccountsResponseDTO> accounts;
    private List<SWBICSResponseDTO> swbics;
}
