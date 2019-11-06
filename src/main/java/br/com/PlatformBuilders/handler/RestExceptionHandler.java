package br.com.PlatformBuilders.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.PlatformBuilders.errors.ResourceNotFoundDetails;
import br.com.PlatformBuilders.errors.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException){
	ResourceNotFoundDetails rnfDetails=	ResourceNotFoundDetails.Builder
		.newBuilder()
		.timestamo(new Date().getTime())
		.status(HttpStatus.NOT_FOUND.value())
		.title("Resource not Found. ")
		.detail(rfnException.getMessage())
		.developerMessage(rfnException.getClass().getName())
		.build();
		return new ResponseEntity<>(rnfDetails,HttpStatus.NOT_FOUND);
	}
}
