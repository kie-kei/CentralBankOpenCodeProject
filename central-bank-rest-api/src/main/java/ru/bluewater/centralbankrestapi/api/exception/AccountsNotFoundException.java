package ru.bluewater.centralbankrestapi.api.exception;

import java.util.UUID;

public class AccountsNotFoundException extends Exception {
    public AccountsNotFoundException(UUID uuid) {
        super("accounts by " + uuid + " not found");
    }

    public AccountsNotFoundException() {
        super("accounts not found");
    }
}
