package ru.bluewater.centralbankopencodeproject.api.dto.response;


import lombok.*;
import ru.bluewater.centralbankopencodeproject.entity.AccRstrListEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsResponseDTO {
    private UUID uuid;
    private String account;
    private String regulationAccountType;
    private String CK;
    private String accountCBRBIC;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String accountStatus;
    private List<AccRstrListResponseDTO> accRstrList;
}

