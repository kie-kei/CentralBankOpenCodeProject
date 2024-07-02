package ru.bluewater.centralbankrestapi.api.exception;

public class IncorrectFileTypeException extends Exception{
    public IncorrectFileTypeException(String fileType){
        super("The file is now " + fileType + ", but expected .xml");
    }
}
