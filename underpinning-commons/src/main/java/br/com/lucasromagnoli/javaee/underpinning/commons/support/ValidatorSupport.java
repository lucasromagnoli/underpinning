package br.com.lucasromagnoli.javaee.underpinning.commons.support;


import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningBadRequestException;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningInternalServerErrorException;
import br.com.lucasromagnoli.javaee.underpinning.commons.validation.Validation;
import br.com.lucasromagnoli.javaee.underpinning.commons.validation.ValidationField;
import br.com.lucasromagnoli.javaee.underpinning.commons.validation.ValidationType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
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
        this.fields.add(new ValidationField(field, ValidationType.OBJECT_NOT_NULL));
        return this;
    }

    public ValidatorSupport fields(String ...fields) {
        for (String field : fields) {
            this.fields.add(new ValidationField(field, ValidationType.OBJECT_NOT_NULL));
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

    public Validation validate() throws UnderpinningException {
        for (ValidationField field : fields) {
            try {
                if (!BooleanSupport.isNull(validation.getDetails()) && !BooleanSupport.isNull(validation.getDetails().get(field.getName()))) {
                    break;
                }

                Object reflectionReturn = reflectionGetterField(field.getName(), target);

                switch (field.getValidationType()) {
                    case OBJECT_NOT_NULL:
                        verifyNullAndEmptyValues(field, reflectionReturn);
                        break;
                    case OBJECT_EQUALS:
                        verifyObjectEquals(field, reflectionReturn, field.getArguments()[0], field.getArguments()[1]);
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
                    case STRING_REGEX_MATCH:
                        verifyStringMatchWithRegexPattern(field, reflectionReturn, field.getArguments()[0], (Integer) field.getArguments()[1]);
                        break;
                    case DATE_BEFORE:
                        verifyDateBefore(field, reflectionReturn, (Date) field.getArguments()[0]);
                        break;
                    case DATE_AFTER:
                        verifyDateAfter(field, reflectionReturn, (Date) field.getArguments()[0]);
                        break;
                    case DATE_BETWEEN:
                        verifyDateSeason(field, reflectionReturn, (Date) field.getArguments()[0], (Date) field.getArguments()[1]);
                        break;
                }

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new UnderpinningInternalServerErrorException();
            }
        }

        return validation;
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

    private void verifyDateAfter(ValidationField field, Object target, Date maxDate) {
        Date targetCasted = (Date) target;

        if (targetCasted.after(maxDate)) {
            validation.rejectField(field.getName(), field.getValidationType().message);
        }
    }

    private void verifyDateBefore(ValidationField field, Object target, Date minDate) {
        Date targetCasted = (Date) target;

        if (targetCasted.before(minDate)) {
            validation.rejectField(field.getName(), field.getValidationType().message);
        }
    }

    private void verifyDateSeason(ValidationField field, Object target, Date minDate, Date maxDate) {
        Date targetCasted = (Date) target;

        if (targetCasted.before(minDate) && targetCasted.after(maxDate)) {
            validation.rejectField(field.getName(), field.getValidationType().message);
        }
    }

    private void verifyObjectEquals(ValidationField field, Object target, Object objectToCompare, Object objectFieldName) {
        if (!target.equals(objectToCompare)) {
            validation.rejectField(field.getName(), field.getValidationType().message);
            validation.rejectField(objectFieldName.toString(), field.getValidationType().message);
        }
    }

    private void verifyStringMatchWithRegexPattern(ValidationField field, Object target, Object regex, int flags) {
        if (!RegexSupport.matcher(target.toString(), regex.toString(), flags)) {
            validation.rejectField(field.getName(), field.getValidationType().message);
        }
    }
}
