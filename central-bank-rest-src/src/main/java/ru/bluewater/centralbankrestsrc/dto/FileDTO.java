package ru.bluewater.centralbankrestsrc.dto;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestsrc.entity.xml.ED807;

@Setter
@Getter
public class FileDTO {
    private String filename;
    private ED807 ed807;

    public FileDTO(String filename, ED807 ed807){
        this.filename = filename;
        this.ed807 = ed807;
    }
}
