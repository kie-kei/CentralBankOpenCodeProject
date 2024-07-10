package ru.bluewater.centralbankrestapi.api.dto.response.read;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InitialEDGetResponseDTO {
    private UUID uuid;
    private Integer edNo; // numberOfElectronicMessage
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD
    private Long edAuthor; // electronicMessageAuthorId unique
}
