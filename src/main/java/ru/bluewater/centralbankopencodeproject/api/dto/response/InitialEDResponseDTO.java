package ru.bluewater.centralbankopencodeproject.api.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;

import java.time.LocalDate;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitialEDResponseDTO {
    private UUID uuid;
    private Integer edNo; // numberOfElectronicMessage
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD
    private Long edAuthor; // electronicMessageAuthorId unique
}
