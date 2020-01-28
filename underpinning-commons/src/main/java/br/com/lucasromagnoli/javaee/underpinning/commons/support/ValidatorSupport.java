package br.com.lucasromagnoli.javaee.underpinning.commons.support;


import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import br.com.lucasromagnoli.javaee.underpinning.commons.model.Validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class ValidatorSupport {
    public static final String REQUIRED_FIELD_MESSAGE = "This is a required field.";
    public static final String REQUIRED_TARGET_MESSAGE = "Please, check your request.";

    private Object target;
    private List<String> fields;

    public ValidatorSupport(Object target) {
        this.fields = new ArrayList<>();
        this.target = target;
    }

    public static <T> Object reflectionGetterField(String field, T target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = new StringBuilder("get")
                .append(StringSupport.firstLetterToUpperCase(field))
                .toString();

        Method method = target.getClass().getMethod(methodName);
        return method.invoke(target);
    }

    public static ValidatorSupport target(Object target) {
        return new ValidatorSupport(target);
    }

    public ValidatorSupport field(String field) {
        this.fields.add(field);
        return this;
    }

    public ValidatorSupport fields(String ...fields) {
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    public ValidatorSupport fields(String fieldCsv) {
        fields(fieldCsv.replace(" ", "").split(","));
        return this;
    }
    public void validateInput() throws UnderpinningException {
        Validator.validateNullAndEmptyValues(target, fields.stream().toArray(String[]::new));
    }
}
