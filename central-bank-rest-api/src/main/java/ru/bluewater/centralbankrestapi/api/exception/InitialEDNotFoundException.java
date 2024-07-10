package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class InitialEDNotFoundException extends Exception {
    public InitialEDNotFoundException(UUID uuid) {
        super("initial ed by " + uuid + " not found");
    }

    public InitialEDNotFoundException() {
        super("initial ed not found");
    }
}
