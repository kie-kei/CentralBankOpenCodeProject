package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class AccRstrListNotFoundException extends Exception {
    public AccRstrListNotFoundException(UUID uuid) {
        super("acc rstr list by " + uuid + " not found");
    }

    public AccRstrListNotFoundException() {
        super("acc rstr list not found");
    }
}
