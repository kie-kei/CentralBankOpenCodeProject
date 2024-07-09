package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;

@Getter
@Setter
public class BicDirectoryEntryUpdateRequestDTO {
    private String BIC;
    private String changeType;

}
