package ru.bluewater.centralbankrestapi.api.dto.response.update;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RstrListUpdateResponseDTO {
    private UUID uuid;
    private String rstr;
    private Date rstrDate;
}
