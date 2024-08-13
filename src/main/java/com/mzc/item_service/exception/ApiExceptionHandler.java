package com.mzc.item_service.exception;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mzc.item_service.dto.ResponseDto;

import jakarta.servlet.http.HttpServletRequest;

/*
 * @ControllerAdvice
 * - @ResponseEntity에서 특정 객체를 json 타입으로 응답하기 위해서 사용됩니다.

 * @RestControllerAdvice
 * - 스프링에서 전역적으로 예외를 처리하는 클래스입니다.
 * - @ResponseBody에서 특정 객체를 json 타입으로 응답하기 위해서 사용됩니다.
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception ex) throws Exception {
        ResponseDto.ResponseDtoBuilder responseBuilder = ResponseDto.builder();
        responseBuilder.code("500").message(ex.getMessage());
        return ResponseEntity.ok(responseBuilder.build());
    }

    /*
     *  유효성 체크에 오류가 발생한 경우 예외 처리 구현
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) throws JSONException {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("] : ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값 : [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        ResponseDto.ResponseDtoBuilder responseBuilder = ResponseDto.builder();
        responseBuilder.code("500").message(builder.toString());
        return ResponseEntity.ok(responseBuilder.build());
        
    }

    /*
     * User Defined Exception
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(HttpServletRequest request, Exception ex) throws Exception {
        ResponseDto.ResponseDtoBuilder responseBuilder = ResponseDto.builder();
        responseBuilder.code("501").message(ex.getMessage());
        return ResponseEntity.ok(responseBuilder.build());
    }

   
}