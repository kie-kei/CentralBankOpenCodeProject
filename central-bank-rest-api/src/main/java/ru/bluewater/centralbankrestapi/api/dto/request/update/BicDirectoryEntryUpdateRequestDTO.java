package ru.bluewater.centralbankrestapi.api.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;

@Getter
@Setter
public class BicDirectoryEntryUpdateRequestDTO {
    @Size(min = 9, max = 9, message = "BIC length should be 9")
    @NotNull(message = "BIC should be not null")
    private String BIC;
    @Size(min = 4, max = 4, message = "changeType length should be 4")
    private String changeType;

}
