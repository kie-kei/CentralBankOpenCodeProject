package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ED807GetResponseDTO;

import java.util.List;

@Getter
@Setter
public class ED807ListResponseDTO {
    private List<ED807GetResponseDTO> rootList;

    public ED807ListResponseDTO(){}
    public ED807ListResponseDTO(List<ED807GetResponseDTO> rootList){
        this.rootList = rootList;
    }
}
