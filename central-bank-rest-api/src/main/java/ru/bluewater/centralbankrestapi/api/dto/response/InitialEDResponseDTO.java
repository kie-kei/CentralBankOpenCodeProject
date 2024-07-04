package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class InitialEDResponseDTO {
    private UUID uuid;
    private Integer edNo; // numberOfElectronicMessage
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD
    private Long edAuthor; // electronicMessageAuthorId unique
}
