package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RstrListUpdateRequestDTO {
    private String rstr;
    private Date rstrDate;
}
