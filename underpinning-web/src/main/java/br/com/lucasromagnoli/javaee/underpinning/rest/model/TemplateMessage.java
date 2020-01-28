package br.com.lucasromagnoli.javaee.underpinning.rest.model;

import br.com.lucasromagnoli.javaee.underpinning.commons.support.BooleanSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class TemplateMessage {
    private MessageType messageType;
    private String message;
    private HttpStatus httpStatus;
    private Object payload;

    public TemplateMessage(MessageType messageType, String message, HttpStatus httpStatus, Object payload) {
        this.messageType = messageType;
        this.message = message;
        this.httpStatus = httpStatus;
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

    public ResponseEntity<TemplateMessage> toResponseEntity() {
        return ResponseEntity.status(BooleanSupport.nullValueLogic(httpStatus, HttpStatus.OK)).body(this);
    }
}
