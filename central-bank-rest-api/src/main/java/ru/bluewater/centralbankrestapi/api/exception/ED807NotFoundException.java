package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class ED807NotFoundException extends Exception{
    public ED807NotFoundException(UUID uuid) { super("ed807 by " + uuid + " not found" );}
    public ED807NotFoundException(){super("ed807 not found");}
}
