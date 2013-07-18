package ru.evo.core.Exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 18.07.13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class InvalidGoalTypeException extends Exception {
    public InvalidGoalTypeException(String message) {
        super("Goal type: " + message);
    }

    public InvalidGoalTypeException() {
    }
}
