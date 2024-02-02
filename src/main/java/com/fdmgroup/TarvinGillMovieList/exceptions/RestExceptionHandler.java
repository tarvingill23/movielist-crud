package com.fdmgroup.TarvinGillMovieList.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle(NotFoundException e) {
		return new ApiErrorResponse(e.getMessage());
	}

	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApiErrorResponse handle(ConflictException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	
	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ApiErrorResponse handle(ForbiddenException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	

}

