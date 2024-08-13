package com.mzc.item_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mzc.item_service.dto.ItemDto;
import com.mzc.item_service.dto.ResponseDto;

import com.mzc.item_service.service.ItemService;
import com.mzc.item_service.valid.ItemTypeValid;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "api/v1/items")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ItemControlller {

    private final ItemService itemService;

   
    // Validation Check
    @PostMapping(value = "/{itemType}")
    public ResponseEntity<ResponseDto> createItem(@Valid @RequestBody ItemDto itemDto, @ItemTypeValid @PathVariable(value = "itemType") String itemType) {
           
        ResponseDto.ResponseDtoBuilder responseBuilder = ResponseDto.builder();

        itemDto.setItemType(itemType);

        itemService.saveIem(itemDto);     

        // 로깅을 위한 log 변수를 자동으로 생성하여 제공한다.

        log.debug("request item id : {}", itemDto.getId());

        responseBuilder.code("200").message("success");
        
        return ResponseEntity.ok(responseBuilder.build());
        
    }
    

}