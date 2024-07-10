package ru.bluewater.centralbankrestapi.api.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountsUpdateRequestDTO {
    @Pattern(regexp = "[0-9]{20}", message = "account length should be 20 and contains only number")
    @NotNull(message = "account should be not null")
    private String account;
    @Size(min = 4, max = 4, message = "RegulationAccountType length should be 4")
    @NotNull(message = "regulationAccountType should be not null")
    private String regulationAccountType;
    @Size(min = 2, max = 2, message = "CK length should be 2")
    private String CK;
    @Size(min = 9, max = 9, message = "AccountCBRBIC length should be 9")
    @NotNull(message = "accountCBRBIC should be not null")
    private String accountCBRBIC;
    @NotNull(message = "DateIn should be not null")
    private LocalDate dateIn;
    private LocalDate dateOut;
    @Size(min = 4, max = 4, message = "AccountStatus length should be 4")
    private String accountStatus;
}
