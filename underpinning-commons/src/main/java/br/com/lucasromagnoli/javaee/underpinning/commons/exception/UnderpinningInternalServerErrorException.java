package br.com.lucasromagnoli.javaee.underpinning.commons.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class UnderpinningInternalServerErrorException extends UnderpinningException {
    public UnderpinningInternalServerErrorException() {
        // TODO: Colocar em uma constante.
        super("Sorry, the server has encountered an internal error and was unable to complete your request.");
    }
    public UnderpinningInternalServerErrorException(String message) {
        super(message);
    }
}
