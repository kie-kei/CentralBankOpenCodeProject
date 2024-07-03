package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class BicDirectoryEntryNotFoundException extends Exception {
    public BicDirectoryEntryNotFoundException(UUID uuid) { super("BicDirectoryEntry by " + uuid + " not found"); }
}
