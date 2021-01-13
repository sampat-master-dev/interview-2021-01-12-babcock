package com.sampat.interview.babcock.vehiclehire.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = "Malformed JSON request";
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  protected ResponseEntity<Object> handleVehicleNotFoundException(VehicleNotFoundException ex) {
    String error = "Vehicle Not Found";
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
