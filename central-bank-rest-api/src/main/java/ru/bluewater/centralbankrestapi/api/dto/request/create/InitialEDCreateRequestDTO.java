package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InitialEDCreateRequestDTO {
    private Integer edNo;
    private LocalDate edDate;
    private Long edAuthor;
}
