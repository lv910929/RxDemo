package com.lv.rxdemo.widget.validator;

/**
 * Interface to implement for custom validation
 */
public interface IValidator {
    boolean isValid(String pText);
    void setErrorMessage(String pErrorMessage);
    String getErrorMessage();
}
