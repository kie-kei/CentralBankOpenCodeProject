package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.request.AccRstrListRequestDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountsCreateRequestDTO {
    private UUID bicDirectoryEntryUuid;
    private String account;
    private String regulationAccountType;
    private String CK;
    private String accountCBRBIC;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String accountStatus;
}
