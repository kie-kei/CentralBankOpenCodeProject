package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class RootNotFoundException extends Exception{
    public RootNotFoundException(UUID uuid) { super("root by " + uuid + " not found" );}
    public RootNotFoundException(){super("root not found");}
}
