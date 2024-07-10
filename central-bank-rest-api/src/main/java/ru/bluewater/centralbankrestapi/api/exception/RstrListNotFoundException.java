package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class RstrListNotFoundException extends Exception {
    public RstrListNotFoundException(UUID uuid) {
        super("rstr list by " + uuid + " not found");
    }

    public RstrListNotFoundException() {
        super("rstr list not found");
    }
}
