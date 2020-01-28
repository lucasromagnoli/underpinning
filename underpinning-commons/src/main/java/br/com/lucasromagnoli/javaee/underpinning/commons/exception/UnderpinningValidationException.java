package br.com.lucasromagnoli.javaee.underpinning.commons.exception;

import br.com.lucasromagnoli.javaee.underpinning.commons.model.Validation;
import br.com.lucasromagnoli.javaee.underpinning.commons.support.ValidationSupport;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class UnderpinningValidationException extends UnderpinningException {
    private Validation validation;

    public UnderpinningValidationException(Validation validation) {
        super(ValidationSupport.getDetails(validation));
        this.validation = validation;
    }

    public Validation getValidation() {
        return validation;
    }
}
