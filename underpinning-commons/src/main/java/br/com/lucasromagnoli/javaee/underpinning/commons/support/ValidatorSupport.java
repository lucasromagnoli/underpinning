package br.com.lucasromagnoli.javaee.underpinning.commons.support;


import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningBadRequestException;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningInternalServerErrorException;
import br.com.lucasromagnoli.javaee.underpinning.commons.validation.Validation;
import br.com.lucasromagnoli.javaee.underpinning.commons.validation.ValidationField;
import br.com.lucasromagnoli.javaee.underpinning.commons.validation.ValidationType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class ValidatorSupport {
    public static final String ARGUMENTS_AMOUNT_INVALID = "Quantity of arguments we expected: [%d], but quantity we receive: [%d]";
    public static final String REQUIRED_TARGET_MESSAGE = "Please, check your request.";

    private Object target;
    private Validation validation;
    private List<ValidationField> fields;

    public ValidatorSupport(Object target) {
        this.fields = new LinkedList<>();
        this.validation = new Validation();
        this.target = target;
    }

    private <T> Object reflectionGetterField(String field, T target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = new StringBuilder("get")
                .append(StringSupport.firstLetterToUpperCase(field))
                .toString();

        Method method = target.getClass().getMethod(methodName);
        return method.invoke(target);
    }

    public static ValidatorSupport target(Object target) throws UnderpinningBadRequestException {
        if (BooleanSupport.isNull(target)) {
            throw new UnderpinningBadRequestException(ValidatorSupport.REQUIRED_TARGET_MESSAGE);
        }

        return new ValidatorSupport(target);
    }

    public ValidatorSupport field(String field) {
        this.fields.add(new ValidationField(field, ValidationType.OBJECT_FIELD_NOT_NULL));
        return this;
    }

    public ValidatorSupport fields(String ...fields) {
        for (String field : fields) {
            this.fields.add(new ValidationField(field, ValidationType.OBJECT_FIELD_NOT_NULL));
        }

        return this;
    }

    public ValidatorSupport field(String field, ValidationType validationType) {
        this.fields.add(new ValidationField(field, validationType));
        return this;
    }

    public ValidatorSupport field(String field, ValidationType validationType, Object ...args) throws UnderpinningInternalServerErrorException {
        if (args.length != validationType.argumentsAmount) {
            throw new UnderpinningInternalServerErrorException(String.format(ARGUMENTS_AMOUNT_INVALID, validationType.argumentsAmount, args.length));
        }

        this.fields.add(new ValidationField(field, validationType, args));
        return this;
    }

    public void validate() throws UnderpinningException {
        for (ValidationField field : fields) {
            try {
                if (!BooleanSupport.isNull(validation.getDetails()) && !BooleanSupport.isNull(validation.getDetails().get(field.getName()))) {
                    break;
                }

                Object reflectionReturn = reflectionGetterField(field.getName(), target);

                switch (field.getValidationType()) {
                    case OBJECT_FIELD_NOT_NULL:
                        verifyNullAndEmptyValues(field, reflectionReturn);
                        break;
                    case STRING_MAX_LENGTH:
                        verifyStringLength(field, reflectionReturn, 1, (Integer) field.getArguments()[0]);
                        break;
                    case STRING_MIN_LENGTH:
                        verifyStringLength(field, reflectionReturn, (Integer) field.getArguments()[0], Integer.MAX_VALUE);
                        break;
                    case STRING_BETWEEN_LENGTH:
                        verifyStringLength(field, reflectionReturn, (Integer) field.getArguments()[0], (Integer) field.getArguments()[1]);
                        break;
                }

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new UnderpinningInternalServerErrorException();
            }
        }
        validation.throwValidationException();
    }

    private void verifyNullAndEmptyValues (ValidationField field, Object target) {
        if (target == null || target.toString().trim().equals("")) {
            validation.rejectField(field.getName(), field.getValidationType().message);
        }
    }

    private void verifyStringLength(ValidationField field, Object target, Integer minLength, Integer maxLength) {
        if (target == null || target.toString().trim().length() > maxLength || target.toString().trim().length() < minLength) {
            validation.rejectField(field.getName(), field.getValidationType().message);
        }
    }
}
