package com.AnnualProject.February.Exception;

public class FileStorageLocationException extends RuntimeException{
    public FileStorageLocationException(String message){
        super(message);
    }
    public FileStorageLocationException(String message, Throwable cause){
        super(message, cause);
    }


}
