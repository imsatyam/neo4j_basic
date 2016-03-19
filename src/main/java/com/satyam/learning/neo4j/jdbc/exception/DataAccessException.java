package com.satyam.learning.neo4j.jdbc.exception;

/**
 * Created by Satyam on 3/19/2016.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause){
        super(message, cause);
    }
}
