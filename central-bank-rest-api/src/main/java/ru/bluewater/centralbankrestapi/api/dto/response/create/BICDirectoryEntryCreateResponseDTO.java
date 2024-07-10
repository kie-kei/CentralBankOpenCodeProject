package ru.bluewater.centralbankrestapi.api.dto.response.create;

import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;

import java.util.UUID;

@Getter
@Setter
public class BICDirectoryEntryCreateResponseDTO {
    private UUID uuid;
    private String BIC;
    private String changeType;
    private ParticipantInfoResponseDTO participantInfo;
}
