package com.toy.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.toy.common.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e){
		String message = e.getBindingResult()
				.getFieldErrors()
				.get(0)
				.getDefaultMessage();
		
		return ResponseEntity.badRequest().body(new ErrorResponse(message));
			
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){
		return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e){
		return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
	}
}
