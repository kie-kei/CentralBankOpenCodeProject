package ru.bluewater.centralbankrestapi.api.dto.response.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InitialEDUpdateResponseDTO {
    private UUID uuid;
    private Integer edNo;
    private LocalDate edDate;
    private Long edAuthor;
}
