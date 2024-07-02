package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RstrListResponseDTO {
    private UUID uuid;
    private String rstr;
    private Date rstrDate;
}