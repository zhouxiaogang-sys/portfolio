package jp.co.sysystem.training.guide.web.controller.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jp.co.sysystem.training.guide.exception.NotFoundException;
import jp.co.sysystem.training.guide.exception.UnauthorizedException;
import jp.co.sysystem.training.guide.web.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(UnauthorizedException.class)
  public String handleUnauthorized() {
    return "redirect:/";
  }
  
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
      ErrorResponse response = new ErrorResponse(
          HttpStatus.NOT_FOUND.value(),
          ex.getMessage(),
          LocalDateTime.now()
      );
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}