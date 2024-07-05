package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RootGetResponseDTO;

import java.util.List;

@Getter
@Setter
public class RootListResponseDTO {
    private List<RootGetResponseDTO> rootList;

    public RootListResponseDTO(){}
    public RootListResponseDTO(List<RootGetResponseDTO> rootList){
        this.rootList = rootList;
    }
}
