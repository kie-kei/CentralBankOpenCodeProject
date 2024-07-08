package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class PartInfoNotFoundException extends Exception {
    public PartInfoNotFoundException(UUID uuid) {
        super("part info by " + uuid + " not found");
    }

    public PartInfoNotFoundException() {
        super("part info not found");
    }
}
