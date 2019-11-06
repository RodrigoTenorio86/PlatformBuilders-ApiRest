package br.com.PlatformBuilders.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.PlatformBuilders.errors.ErrorDetails;
import br.com.PlatformBuilders.errors.ResourceNotFoundDetails;
import br.com.PlatformBuilders.errors.ResourceNotFoundException;
import br.com.PlatformBuilders.errors.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
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
	
	
	
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException rfnException, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
	  List<FieldError>fieldErrors = rfnException.getBindingResult().getFieldErrors();
	  String field =  fieldErrors.parallelStream().map(FieldError::getField).collect(Collectors.joining(","));
	  String fieldMessages =  fieldErrors.parallelStream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
	  
		
		ValidationErrorDetails rnfDetails=	ValidationErrorDetails.Builder
		.newBuilder()
		.timestamo(new Date().getTime())
		.status(HttpStatus.BAD_REQUEST.value())
		.title("Field Validation Error. ")
		.detail("Field Validation Error. ")
		.developerMessage(rfnException.getClass().getName())
		.field(field)
		.fieldMessage(fieldMessages)
		.build();
		return new ResponseEntity<>(rnfDetails,HttpStatus.BAD_REQUEST);
	}
    
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, 
			@Nullable Object body, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
    	
    	
    	ErrorDetails errorDetails=	ErrorDetails.Builder
    			.newBuilder()
    			.timestamo(new Date().getTime())
    			.status(status.value())
    			.title("Resource not Found. ")
    			.detail(ex.getMessage())
    			.developerMessage(ex.getClass().getName())
    			.build();
    			return new ResponseEntity<>(errorDetails,headers,status);
    	
    	
    	
    }

	
	
	
	
}
