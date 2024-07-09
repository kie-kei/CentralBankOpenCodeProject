package ru.bluewater.centralbankrestapi.api.dto.response.read;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;

import java.util.UUID;

@Getter
@Setter
public class BICDirectoryEntryGetResponseDTO {
    private UUID uuid;
    private String BIC;
    private String changeType;
    private ParticipantInfoGetResponseDTO participantInfo;
}