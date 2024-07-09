package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.bluewater.centralbankrestapi.api.dto.request.create.AccountsCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccountsUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccountsCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.AccountsListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccountsUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestsrc.service.AccountsService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping("/accounts/{accounts_uuid}")
    public AccountsGetResponseDTO findAccountsByUuid(
            @PathVariable("accounts_uuid") UUID uuid
    ) throws AccountsNotFoundException {
        return accountsService.findAccountsByUuid(uuid);
    }

    @PutMapping("/accounts/{accounts_uuid}")
    public AccountsUpdateResponseDTO updateAccountsByUuid(
            @PathVariable("accounts_uuid") UUID uuid,
            @RequestBody AccountsUpdateRequestDTO requestDTO
    ) throws AccountsNotFoundException {
        return accountsService.updateAccounts(uuid, requestDTO);
    }

    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/accounts")
    public AccountsListResponseDTO findListAccounts(
            @PathVariable("bic_directory_entry_uuid") UUID uuid
    ) throws BicDirectoryEntryNotFoundException {
        return accountsService.findListAccounts(uuid);
    }

    @PostMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/accounts")
    public AccountsCreateResponseDTO createAccounts(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody AccountsCreateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException {
        return accountsService.createAccounts(uuid, requestDTO);
    }
}
