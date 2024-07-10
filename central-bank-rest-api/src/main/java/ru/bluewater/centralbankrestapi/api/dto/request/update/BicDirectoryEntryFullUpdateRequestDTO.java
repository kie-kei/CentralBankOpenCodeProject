package ru.bluewater.centralbankrestapi.api.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 9, max = 9, message = "BIC length should be 9")
    @NotNull(message = "BIC should be not null")
    private String BIC;
    @Size(min = 4, max = 4, message = "changeType length should be 4")
    private String changeType;
    private ParticipantInfoRequestDTO participantInfo;
    private List<AccountsRequestDTO> accounts;
    private List<SWBICSRequestDTO> swbics;
}
