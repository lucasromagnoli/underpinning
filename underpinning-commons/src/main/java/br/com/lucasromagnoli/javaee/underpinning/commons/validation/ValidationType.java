package br.com.lucasromagnoli.javaee.underpinning.commons.validation;

/**
 * @author github.com/lucasromagnoli
 * @since 28/01/2020
 */
public enum ValidationType {
    OBJECT_FIELD_NOT_NULL(0, "This is a required field."),
    STRING_MAX_LENGTH(1, "This field has the wrong size."),
    STRING_MIN_LENGTH(1, "This field has the wrong size."),
    STRING_BETWEEN_LENGTH(2, "This field has the wrong size.");

    public final Integer argumentsAmount;
    public final String message;

    ValidationType(Integer argumentsAmount, String message) {
        this.argumentsAmount = argumentsAmount;
        this.message = message;
    }

}
