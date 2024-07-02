package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BICDirectoryEntryResponseDTO {
    private UUID uuid;
    private String BIC;
    private String changeType;
    private ParticipantInfoResponseDTO participantInfo;
    private List<AccountsResponseDTO> accounts;
    private List<SWBICSResponseDTO> swbics;

}
