package com.assignment.coffeemaker.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         16/04/21
 */
public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message) {
        super(message);
    }
}
