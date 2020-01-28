package br.com.lucasromagnoli.javaee.underpinning.commons.support;

import br.com.lucasromagnoli.javaee.underpinning.commons.model.Validation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class ValidationSupport {
    private Map<String, String> details;

    public static ValidationSupport begin() {
        return new ValidationSupport();
    }

    public ValidationSupport details(Map<String, String> details) {
        this.details = details;
        return this;
    }

    public ValidationSupport details(String field, String fieldMessage) {
        if (details == null) {
            details = new HashMap<>();
        }

        details.put(field, fieldMessage);
        return this;
    }

    public Validation build() {
        return new Validation(details);
    }

    public static String getDetails(Validation validation) {
        StringBuilder details = new StringBuilder("Details: ");
        validation.getDetails().forEach((field, fieldMessage) -> {
            details.append(String.format("[%s] -> %s.", field, fieldMessage));
            details.append(System.getProperty("line.separator"));
        });

        return details.toString();
    }
}
