package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SWBICSListResponseDTO {
    private List<SWBICSGetResponseDTO> swbicsList;

    public SWBICSListResponseDTO(){}

    public SWBICSListResponseDTO(List<SWBICSGetResponseDTO> swbicsList){
        this.swbicsList = swbicsList;
    }
}
