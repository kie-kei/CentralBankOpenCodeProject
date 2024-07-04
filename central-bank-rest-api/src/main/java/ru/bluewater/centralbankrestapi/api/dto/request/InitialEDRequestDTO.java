package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class InitialEDRequestDTO {
    private UUID uuid;
    private Integer edNo; // numberOfElectronicMessage
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD
    private Long edAuthor; // electronicMessageAuthorId unique
}
