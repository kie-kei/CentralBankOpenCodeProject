package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class ParticipantInfoNotFoundException extends Exception {
    public ParticipantInfoNotFoundException(UUID uuid) {
        super("participant info by " + uuid + " not found");
    }

    public ParticipantInfoNotFoundException() {
        super("participant info not found");
    }
}
