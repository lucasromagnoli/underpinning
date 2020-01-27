package br.com.lucasromagnoli.javaee.underpinning.commons.exception;

import br.com.lucasromagnoli.javaee.underpinning.commons.model.Validation;

public class UnderpinningBusinessValidationException extends UnderpinningValidationException {
    public UnderpinningBusinessValidationException(Validation validation) {
        super(validation);
    }
}
