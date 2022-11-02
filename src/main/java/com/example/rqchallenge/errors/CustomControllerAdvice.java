package com.example.rqchallenge.errors;


import org.apache.tomcat.util.json.ParseException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {



    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<Object> handleInterruptedExceptionException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("The connection was interrupted");
        errorResponse.setStackTrace(String.valueOf(e));
        errorResponse.setStatus(String.valueOf(HttpStatus.REQUEST_TIMEOUT));
        return this.buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<Object> handleParseExceptionException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Could not parse the object correctly");
        errorResponse.setStackTrace(String.valueOf(e));
        errorResponse.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
        return this.buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<Object> handleURISyntaxExceptionException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Api url could not be parsed");
        errorResponse.setStackTrace(String.valueOf(e));
        errorResponse.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
        return this.buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOExceptionException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Could not read data");
        errorResponse.setStackTrace(String.valueOf(e));
        errorResponse.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
        return this.buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<Object> handleCustomErrorExceptions(Exception e) {
        // casting the generic Exception e to CustomErrorException
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("big problem here!");
        errorResponse.setStackTrace(e.toString());
        return this.buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        "The list was empty or came back null",
                        stackTrace
                ),
                status
        );
    }

    // fallback method
    @ExceptionHandler(RuntimeException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        "Runtime exception",
                        stackTrace
                ),
                status
        );
    }

    // fallback method
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity<ErrorResponse> handleExceptions(
            Exception e
    ) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}
