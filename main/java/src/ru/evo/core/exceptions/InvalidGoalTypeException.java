package ru.evo.core.exceptions;

public class InvalidGoalTypeException extends Exception {
    public InvalidGoalTypeException(String message) {
        super("Goal type: " + message);
    }

    public InvalidGoalTypeException() {
    }
}
