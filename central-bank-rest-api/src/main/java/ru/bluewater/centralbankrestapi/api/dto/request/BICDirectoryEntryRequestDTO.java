package ru.bluewater.centralbankrestapi.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class BICDirectoryEntryRequestDTO {
    @Size(min = 9, max = 9, message = "BIC length should be 9")
    @NotNull(message = "BIC should be not null")
    private String BIC;

    @Size(min = 4, max = 4, message = "changeType length should be 4")
    private String changeType;

    @NotNull(message = "participantInfo should be not null")
    private ParticipantInfoResponseDTO participantInfo;
    private List<AccountsResponseDTO> accounts;
    private List<SWBICSResponseDTO> swbics;
}
