package ru.bluewater.centralbankopencodeproject.api.dto.response;

import lombok.*;

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
