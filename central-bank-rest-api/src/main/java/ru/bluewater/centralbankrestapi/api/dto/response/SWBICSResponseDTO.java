package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SWBICSResponseDTO {
    private UUID uuid;
    private String swbic;
    private Boolean defaultSWBIC;
}
