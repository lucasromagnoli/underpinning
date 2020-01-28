package br.com.lucasromagnoli.javaee.underpinning.commons.model;

import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningBadRequestException;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningInternalServerErrorException;
import br.com.lucasromagnoli.javaee.underpinning.commons.support.BooleanSupport;
import br.com.lucasromagnoli.javaee.underpinning.commons.support.ValidatorSupport;

import java.lang.reflect.InvocationTargetException;

import static br.com.lucasromagnoli.javaee.underpinning.commons.support.ValidatorSupport.reflectionGetterField;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class Validator {

    public static <T> void validateNullAndEmptyValues(T target, String ...fields) throws UnderpinningException {
        if (BooleanSupport.isNull(target)) {
            throw new UnderpinningBadRequestException(ValidatorSupport.REQUIRED_TARGET_MESSAGE);
        }

        Validation validation = new Validation();
        for (String field : fields) {
            try {
                Object reflectionReturn = reflectionGetterField(field, target);
                if (reflectionReturn == null || reflectionReturn.toString().trim().equals("")) {
                    validation.rejectField(field, ValidatorSupport.REQUIRED_FIELD_MESSAGE);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new UnderpinningInternalServerErrorException();
            }
        }
        validation.throwInputException();
    }
}
