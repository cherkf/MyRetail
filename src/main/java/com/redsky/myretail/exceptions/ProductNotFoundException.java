package com.redsky.myretail.exceptions;

public class ProductNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 5776681206288518465L;

    public ProductNotFoundException(String message)
    {
        super(message);
    }

}
