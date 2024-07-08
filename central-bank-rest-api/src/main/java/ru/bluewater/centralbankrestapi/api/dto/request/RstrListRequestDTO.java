package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class RstrListRequestDTO {
    private String rstr;
    private Date rstrDate;
}
