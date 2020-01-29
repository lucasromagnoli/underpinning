package br.com.lucasromagnoli.javaee.underpinning.commons.validation;

import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningValidationException;
import br.com.lucasromagnoli.javaee.underpinning.commons.support.BooleanSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class Validation {
    private Map<String, String> details;

    public Validation(Map<String, String> details) {
        this.details = details;
    }

    public Validation() {}

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public void rejectField(String field, String fieldMessage) {
        if (BooleanSupport.isNull(details)) {
            setDetails(new HashMap<>());
        }

        details.put(field, fieldMessage);
    }

    public void throwValidationException() throws UnderpinningValidationException {
        if (!BooleanSupport.isNull(details) && !details.isEmpty()) {
            throw new UnderpinningValidationException(this);
        }
    }
}
