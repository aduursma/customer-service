package nl.agility.customer.ui;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse handleConstraintValidationException(ConstraintViolationException exception) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        exception.getConstraintViolations().forEach(violation -> error.getViolations().add(
            new Violation(violation.getPropertyPath().toString(), violation.getMessage())
        ));
        return error;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> error.getViolations().add(
            new Violation(fieldError.getField(), fieldError.getDefaultMessage())
        ));
        return new ResponseEntity<>(error, headers, status);
    }

    @Data
    class ValidationErrorResponse {
        private final List<Violation> violations = new ArrayList<>();
    }

    @Data
    class Violation {
        private final String fieldName;
        private final String message;
    }

}
