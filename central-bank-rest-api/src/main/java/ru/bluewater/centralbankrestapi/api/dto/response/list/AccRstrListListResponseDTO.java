package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccRstrListGetResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccRstrListListResponseDTO {
    private List<AccRstrListGetResponseDTO> accRstrListList;

    public AccRstrListListResponseDTO(){}

    public AccRstrListListResponseDTO(List<AccRstrListGetResponseDTO> accRstrListList){
        this.accRstrListList = accRstrListList;
    }
}
