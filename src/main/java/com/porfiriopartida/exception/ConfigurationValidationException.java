package com.porfiriopartida.exception;

public class ConfigurationValidationException extends Throwable {
    private String message;
    public ConfigurationValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
