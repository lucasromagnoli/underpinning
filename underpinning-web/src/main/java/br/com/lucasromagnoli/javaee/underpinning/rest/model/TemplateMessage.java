package br.com.lucasromagnoli.javaee.underpinning.rest.model;

import br.com.lucasromagnoli.javaee.underpinning.commons.support.BooleanSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public class TemplateMessage {
    private MessageType messageType;
    private String message;
    private HttpStatus httpStatus;
    private Map<String, String> validation;
    private Object payload;

    public TemplateMessage(MessageType messageType, String message, HttpStatus httpStatus, Map<String, String> validation, Object payload) {
        this.messageType = messageType;
        this.message = message;
        this.httpStatus = httpStatus;
        this.validation = validation;
        this.payload = payload;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Map<String, String> getValidation() {
        return validation;
    }

    public void setValidation(Map<String, String> validation) {
        this.validation = validation;
    }

    public ResponseEntity<TemplateMessage> toResponseEntity() {
        return ResponseEntity.status(BooleanSupport.nullValueLogic(httpStatus, HttpStatus.OK)).body(this);
    }
}
