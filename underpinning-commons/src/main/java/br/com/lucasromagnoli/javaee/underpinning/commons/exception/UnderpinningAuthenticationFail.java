package br.com.lucasromagnoli.javaee.underpinning.commons.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 07/02/2020
 */
public class UnderpinningAuthenticationFail extends UnderpinningException {

    public UnderpinningAuthenticationFail() {
    }

    public UnderpinningAuthenticationFail(String message) {
        super(message);
    }

    public UnderpinningAuthenticationFail(String message, Throwable throwable) {
        super(message, throwable);
    }
}
