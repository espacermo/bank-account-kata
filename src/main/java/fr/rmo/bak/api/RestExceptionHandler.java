package fr.rmo.bak.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.rmo.bak.common.FunctionalException;

/**
 * Manage exception of the application
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler({ FunctionalException.class })
	@ResponseBody
	public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
		LOGGER.error("Functionnal error detected", e);
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}