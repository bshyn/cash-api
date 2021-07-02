package ar.com.frupp.cashapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiValidation {
    private final HttpStatus status;
    private final String errorCode;
    private final String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private final LocalDateTime timestamp;
    private final Map<String, String> validations;

    public ApiValidation(HttpStatus status, String path ,String errorCode,
                         Map<String, String> validations) {
        this.status = status;
        this.path = path;
        this.errorCode = errorCode;
        this.validations = validations;

        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, String> getValidations() {
        return validations;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String validationsStr;
        try {
            validationsStr = mapper.writeValueAsString(validations);
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger(ApiValidation.class).error("Error processing ApiValidation.toString()", e);
            validationsStr = "null";
        }


        return "ApiValidation{" +
                "status=" + status.toString() +
                ", errorCode='" + errorCode + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp.toString() +
                ", validations=" + validationsStr +
                '}';
    }
}
