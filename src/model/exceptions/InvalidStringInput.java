package model.exceptions;

// throw when input is invalid; meaning not all letters are lowercase and incorrect spelling; not expected input throw
// where would i catch this???
public class InvalidStringInput extends Exception {
    public InvalidStringInput(String msg){
        super(msg);
    }
}
