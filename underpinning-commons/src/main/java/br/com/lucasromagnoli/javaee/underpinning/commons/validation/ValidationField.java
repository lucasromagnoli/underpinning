package br.com.lucasromagnoli.javaee.underpinning.commons.validation;

/**
 * @author github.com/lucasromagnoli
 * @since 28/01/2020
 */
public class ValidationField {
    private String name;
    private ValidationType validationType;
    private Object[] arguments;

    public ValidationField(String name, ValidationType validationType, Object[] arguments) {
        this.name = name;
        this.validationType = validationType;
        this.arguments = arguments;
    }

    public ValidationField(String fieldName, ValidationType validationType) {
        this.name = fieldName;
        this.validationType = validationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(ValidationType validationType) {
        this.validationType = validationType;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
