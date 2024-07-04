package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;
import ru.bluewater.centralbankrestapi.api.dto.response.AccRstrListResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountsRequestDTO {
    private UUID uuid;
    private String account;
    private String regulationAccountType;
    private String CK;
    private String accountCBRBIC;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String accountStatus;
    private List<AccRstrListRequestDTO> accRstrList;
}
