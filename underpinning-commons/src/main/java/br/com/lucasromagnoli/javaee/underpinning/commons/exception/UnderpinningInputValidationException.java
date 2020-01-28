package br.com.lucasromagnoli.javaee.underpinning.commons.exception;

import br.com.lucasromagnoli.javaee.underpinning.commons.model.Validation;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class UnderpinningInputValidationException extends UnderpinningValidationException {
    public UnderpinningInputValidationException(Validation validation) {
        super(validation);
    }
}
