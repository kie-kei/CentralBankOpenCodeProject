package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccRstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccRstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccRstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.AccRstrListListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccRstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccRstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccRstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;
import ru.bluewater.centralbankrestsrc.service.AccRstrListService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccRstrListControllerImpl {
    private final AccRstrListService accRstrListService;

    @GetMapping("/accRstrList/{acc_rstr_list_uuid}")
    public AccRstrListGetResponseDTO findAccRstrListByUuid(
            @PathVariable("acc_rstr_list_uuid") UUID uuid
    ) throws AccRstrListNotFoundException {
        return accRstrListService.findAccRstrListByUuid(uuid);
    }

    @PutMapping("/accRstrList/{acc_rstr_list_uuid}")
    public AccRstrListUpdateResponseDTO updateAccRstrListByUuid(
            @PathVariable("acc_rstr_list_uuid") UUID uuid,
            @RequestBody AccRstrListUpdateRequestDTO requestDTO
    ) throws AccRstrListNotFoundException {
        return accRstrListService.updateAccRstrList(uuid, requestDTO);
    }

    @GetMapping("/accounts/{accounts_uuid}/accRstrList")
    public AccRstrListListResponseDTO findListAccRstrList(
            @PathVariable("accounts_uuid") UUID uuid
    ) throws AccountsNotFoundException {
        return accRstrListService.findListAccRstrList(uuid);
    }

    @PostMapping("/accounts/{accounts_uuid}/accRstrList")
    public AccRstrListCreateResponseDTO createAccRstrList(
            @PathVariable("accounts_uuid") UUID uuid,
            @RequestBody AccRstrListCreateRequestDTO requestDTO
    ) throws AccountsNotFoundException {
        return accRstrListService.createAccRstrList(uuid, requestDTO);
    }
}
