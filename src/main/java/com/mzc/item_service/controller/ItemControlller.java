package com.mzc.item_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mzc.item_service.dto.ItemDto;
import com.mzc.item_service.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "api/v1/items")
@Slf4j
public class ItemControlller {
   
    @PostMapping
    public ResponseEntity<ResponseDto> createItem(@RequestBody ItemDto itemDto) {

               
        ResponseDto.ResponseDtoBuilder responseBuilder = ResponseDto.builder();

        // 로깅을 위한 log 변수를 자동으로 생성하여 제공한다.

        log.debug("request item id : {}", itemDto.getId());

        responseBuilder.code("200").message("success");
        
        return ResponseEntity.ok(responseBuilder.build());
        
    }
    

}