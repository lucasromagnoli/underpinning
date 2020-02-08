package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.rest.factory.RestObjectMapperFactory;
import br.com.lucasromagnoli.javaee.underpinning.rest.model.MessageType;
import br.com.lucasromagnoli.javaee.underpinning.rest.model.TemplateMessage;
import br.com.lucasromagnoli.javaee.underpinning.rest.support.TemplateMessageSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author github.com/lucasromagnoli
 * @since 07/02/2020
 */
public class JwtExceptionSupport {
    private JwtExceptionSupport() {}

    private static ObjectMapper restObjectMapper = RestObjectMapperFactory.createObjectMapper();
    private static final Logger logger = LogManager.getLogger(JwtExceptionSupport.class);

    public static void createErrorResponse(HttpServletResponse httpServletResponse, String message, HttpStatus httpStatus, MessageType messageType) throws IOException {
        logger.warn("[---- VALIDATION ERROR OCCURRED ----]");

        TemplateMessage templateMessage = TemplateMessageSupport.begin()
                .messageType(messageType)
                .httpStatus(httpStatus)
                .message(message)
                .build();

        String templateMessageAsJson = restObjectMapper.writeValueAsString(templateMessage);
        httpServletResponse.setStatus(httpStatus.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(templateMessageAsJson);
    }

}
