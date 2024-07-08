package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class SWBICSNotFoundException extends Exception {
    public SWBICSNotFoundException(UUID uuid) {
        super("swbics by " + uuid + " not found");
    }

    public SWBICSNotFoundException() {
        super("swbics not found");
    }
}
