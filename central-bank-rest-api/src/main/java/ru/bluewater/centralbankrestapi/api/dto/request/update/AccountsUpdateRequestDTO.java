package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountsUpdateRequestDTO {
    private String account;
    private String regulationAccountType;
    private String CK;
    private String accountCBRBIC;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String accountStatus;
}
