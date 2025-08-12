package org.VarunSeemar.digital_library.exceptions;

public class NoActiveOrPausedMembershipException extends RuntimeException {
    public NoActiveOrPausedMembershipException(String message) {
        super(message);
    }
}
