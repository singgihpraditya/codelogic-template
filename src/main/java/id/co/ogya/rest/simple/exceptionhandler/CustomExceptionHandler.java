package id.co.ogya.rest.simple.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import id.co.ogya.rest.simple.response.BaseOutputSchema;
import id.co.ogya.rest.simple.util.Constants;
import id.co.ogya.rest.simple.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private ResponseUtils<BaseOutputSchema> responseUtils;
	
	@Override
	protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		StringBuffer details = new StringBuffer();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			FieldError fieldError = (FieldError) error;
			details.append(fieldError.getField());
			details.append(" : ");
			details.append(fieldError.getDefaultMessage());
			details.append(", ");
		}
		return responseUtils.generateFailedResult(new BaseOutputSchema(), new Exception(details.toString()));
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
		log.debug("Error : {}", ex.getMessage());
		ex.printStackTrace();
		return responseUtils.generateFailedResult(new BaseOutputSchema(), new Exception(Constants.ErrorCode.ERROR_MESSAGE_UNDER_MAINTENANCE));
	}
}
