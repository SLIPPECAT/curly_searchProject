package com.curly.search.exception;

import com.curly.search.user.StatusResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> illegalArgumentException(IllegalArgumentException e){
        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        statusResponseDto.setMsg(e.getMessage());
        return new ResponseEntity<>(statusResponseDto, HttpStatus.BAD_REQUEST);
    }
}