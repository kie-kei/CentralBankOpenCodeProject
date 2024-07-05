package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.ED807ListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestapi.controller.RootController;
import ru.bluewater.centralbankrestsrc.service.RootService;

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
    public ED807ResponseDTO findRootByUuid(@PathVariable("uuid") UUID uuid) throws RootNotFoundException {
        return rootService.findRootByUuid(uuid);
    }

    @PutMapping(value = "/{uuid}") //заменить на RootUpdateResponseDTO
    public ED807UpdateResponseDTO updateRoot(@PathVariable("uuid") UUID uuid, @RequestBody ED807UpdateRequestDTO requestDTO)
            throws RootNotFoundException
    {
        return rootService.updateRoot(uuid, requestDTO);
    }
    @GetMapping
    public ED807ListResponseDTO findRootList(){ // public List<RootGetResponseDTO> findRootList() hz kak pravil`no
        return rootService.findRootList();
    }
}