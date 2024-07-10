package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.AccRstrListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountsListResponseDTO {
    private List<AccountsGetResponseDTO> accountsList;

    public AccountsListResponseDTO(List<AccountsGetResponseDTO> accountsList) {
        this.accountsList = accountsList;
    }

    public AccountsListResponseDTO(){}
}
