package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RootUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.RootListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RootUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestapi.controller.RootController;
import ru.bluewater.centralbankrestsrc.service.RootService;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/root")
public class RootControllerImpl implements RootController {
    private final RootService rootService;

    @Autowired
    public RootControllerImpl(RootService rootService) {
        this.rootService = rootService;
    }

    @GetMapping(value = "/{uuid}")
    public RootResponseDTO findRootByUuid(@PathVariable("uuid") UUID uuid) throws RootNotFoundException {
        return rootService.findRootByUuid(uuid);
    }

    @PatchMapping(value = "/{uuid}") //заменить на RootUpdateResponseDTO
    public RootUpdateResponseDTO updateRoot(@PathVariable("uuid") UUID uuid, @RequestBody RootUpdateRequestDTO requestDTO)
            throws RootNotFoundException
    {
        return rootService.updateRoot(uuid, requestDTO);
    }
    @GetMapping
    public RootListResponseDTO findRootList(){ // public List<RootGetResponseDTO> findRootList() hz kak pravil`no
        return rootService.findRootList();
    }
}