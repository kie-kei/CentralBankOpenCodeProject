package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.ED807ListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.controller.ED807Controller;
import ru.bluewater.centralbankrestsrc.service.ED807Service;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/ed807")
public class ED807ControllerImpl implements ED807Controller {
    private final ED807Service ed807Service;

    @Autowired
    public ED807ControllerImpl(ED807Service ed807Service) {
        this.ed807Service = ed807Service;
    }

//    @PostMapping
//    public ED807ResponseDTO createED807(@RequestBody ED807CreateRequestDTO requestDTO, Principal principal){
//        return ed807Service.createED807(requestDTO, principal);
//    }

    @GetMapping(value = "/{uuid}")
    public ED807ResponseDTO findED807ByUuid(@PathVariable("uuid") UUID uuid) throws ED807NotFoundException {
        return ed807Service.findRootByUuid(uuid);
    }

    @PutMapping(value = "/{uuid}") // заменить на RootUpdateResponseDTO
    public ED807UpdateResponseDTO updateED807(@PathVariable("uuid") UUID uuid, @RequestBody ED807UpdateRequestDTO requestDTO)
            throws ED807NotFoundException
    {
        return ed807Service.updateRoot(uuid, requestDTO);
    }
    @GetMapping
    public ED807ListResponseDTO findED807List(){ // public List<RootGetResponseDTO> findRootList() hz kak pravil`no
        return ed807Service.findRootList();
    }
    @DeleteMapping("/{uuid}")
    public void deleteED807(@PathVariable("uuid") UUID uuid) throws ED807NotFoundException {
        ed807Service.deleteED807(uuid);
    }
}