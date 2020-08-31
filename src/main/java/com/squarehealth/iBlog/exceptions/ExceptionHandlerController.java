package com.squarehealth.iBlog.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.squarehealth.iBlog.models.helpers.ApiResponse;
import com.squarehealth.iBlog.utils.Utils;

@ControllerAdvice
public class ExceptionHandlerController {
	
	private static final Logger log = Logger.getLogger(ExceptionHandlerController.class);

		
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processValidationError(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());


        
        return new ApiResponse(false, errors, ex.getClass().getName(), Utils.resolvePathFromWebRequest(request));
    }
	
	
	    @ExceptionHandler(value = ResourceNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	        return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), Utils.resolvePathFromWebRequest(request));
	    }
	    
	    
	    
	    	    
	
}
