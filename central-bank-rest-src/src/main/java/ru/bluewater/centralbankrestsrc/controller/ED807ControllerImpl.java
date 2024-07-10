package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.ED807ListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ED807GetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.controller.ED807Controller;
import ru.bluewater.centralbankrestsrc.service.ED807Service;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/ed807")
@CrossOrigin
public class ED807ControllerImpl implements ED807Controller {
    private final ED807Service ed807Service;

    @Autowired
    public ED807ControllerImpl(ED807Service ed807Service) {
        this.ed807Service = ed807Service;
    }

    @GetMapping("/{uuid}")
    public ED807GetResponseDTO findEd807ByUuid(@PathVariable("uuid") UUID uuid) throws ED807NotFoundException
    {
        return ed807Service.findEd807ByUuid(uuid);
    }
    @GetMapping("/{uuid}/full")
    public ED807ResponseDTO findFullEd807ByUuid(@PathVariable("uuid") UUID uuid) throws ED807NotFoundException {
        return ed807Service.findFullEd807ByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public ED807UpdateResponseDTO updateEd807(
            @PathVariable("uuid") UUID uuid,
            @RequestBody ED807UpdateRequestDTO requestDTO
    ) throws ED807NotFoundException {
        return ed807Service.updateRoot(uuid, requestDTO);
    }
    @GetMapping
    public ED807ListResponseDTO findEd807List(){ // public List<RootGetResponseDTO> findRootList() hz kak pravil`no
        return ed807Service.findEd807List();
    }
    @DeleteMapping("/{uuid}")
    public void deleteED807(@PathVariable("uuid") UUID uuid) throws ED807NotFoundException {
        ed807Service.deleteED807(uuid);
    }
}