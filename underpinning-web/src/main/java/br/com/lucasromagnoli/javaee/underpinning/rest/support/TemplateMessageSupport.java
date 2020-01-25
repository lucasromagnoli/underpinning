package br.com.lucasromagnoli.javaee.underpinning.rest.support;

import br.com.lucasromagnoli.javaee.underpinning.rest.model.MessageType;
import br.com.lucasromagnoli.javaee.underpinning.rest.model.TemplateMessage;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class TemplateMessageSupport {
    private HttpStatus httpStatus;
    private String message;
    private MessageType messageType;
    private Object payload;
    private Map<String, String> validation;

    public static TemplateMessageSupport begin() {
        return new TemplateMessageSupport();
    }

    public TemplateMessageSupport httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public TemplateMessageSupport message(String message) {
        this.message = message;
        return this;
    }

    public TemplateMessageSupport messageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public TemplateMessageSupport payload(Object payload) {
        this.payload = payload;
        return this;
    }

    public TemplateMessageSupport validation(Map<String, String> validation) {
        this.validation = validation;
        return this;
    }

    public TemplateMessageSupport validation(String field, String validationMessage) {
        if (this.validation == null) {
            this.validation = new HashMap<>();
        }

        this.validation.put(field, validationMessage);
        return this;
    }

    public TemplateMessage build() {
            return new TemplateMessage(messageType, message, httpStatus, validation, payload);
    }
}
