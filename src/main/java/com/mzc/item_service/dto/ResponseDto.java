package com.mzc.item_service.dto;

import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class ResponseDto {
    
    private String code;
    private String message;


}