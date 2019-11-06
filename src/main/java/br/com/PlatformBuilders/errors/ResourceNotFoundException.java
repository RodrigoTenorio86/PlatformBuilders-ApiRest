package br.com.PlatformBuilders.errors;
/**
 * @author RTenorio
 * 
 * class lança exception quando nao encontra recursos.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String message) {
	  super(message);
  }
}
