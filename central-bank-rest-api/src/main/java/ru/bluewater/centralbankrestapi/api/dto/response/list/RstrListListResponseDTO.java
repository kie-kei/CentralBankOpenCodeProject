package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RstrListGetResponseDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RstrListListResponseDTO {
    private List<RstrListGetResponseDTO> rstrListList;

    public RstrListListResponseDTO() {
    }

    public RstrListListResponseDTO(List<RstrListGetResponseDTO> rstrListList) {
        this.rstrListList = rstrListList;
    }
}