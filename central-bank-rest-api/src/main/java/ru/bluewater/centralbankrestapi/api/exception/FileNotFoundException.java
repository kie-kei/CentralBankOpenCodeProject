package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class FileNotFoundException extends Exception{
    public FileNotFoundException(UUID uuid) { super("file by " + uuid + " not found" );}
    public FileNotFoundException(){super("file not found");}
}
