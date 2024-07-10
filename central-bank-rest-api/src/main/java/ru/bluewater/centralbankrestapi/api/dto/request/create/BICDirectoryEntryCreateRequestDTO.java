package ru.bluewater.centralbankrestapi.api.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BICDirectoryEntryCreateRequestDTO {
    @Size(min = 9, max = 9, message = "BIC length should be 9")
    @NotNull(message = "BIC should be not null")
    private String BIC;
    @Size(min = 4, max = 4, message = "changeType length should be 4")
    private String changeType;
    @NotNull(message = "participantInfo should be not null")
    private ParticipantInfoCreateRequestDTO participantInfo;
}
