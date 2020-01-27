package br.com.lucasromagnoli.javaee.underpinning.commons.exception;

import br.com.lucasromagnoli.javaee.underpinning.commons.model.Validation;

public class UnderpinningInputValidationException extends UnderpinningValidationException {
    public UnderpinningInputValidationException(Validation validation) {
        super(validation);
    }
}
