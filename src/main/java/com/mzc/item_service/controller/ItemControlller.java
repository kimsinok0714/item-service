package com.mzc.item_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mzc.item_service.dto.ItemDto;
import com.mzc.item_service.dto.ResponseDto;
import com.mzc.item_service.service.ItemService;
import com.mzc.item_service.valid.ItemTypeValid;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@OpenAPIDefinition(info = @Info(title = "Item Service API", description = "물품 처리 요청 API", version = "v1"))
@RestController
@RequestMapping(value = "api/v1/items")
@Slf4j
@RequiredArgsConstructor
@Validated
@RefreshScope
public class ItemControlller {

    private final ItemService itemService;

    
    // Validation Check
    @Operation(summary = "아이템 등록 요청", description = "아이템 등록을 처리한다.", tags = {"createItem"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "SUCCESS"),
        @ApiResponse(responseCode = "501", description = "ApiException")
    })    
    @PostMapping(value = "/{itemType}")
    public ResponseEntity<ResponseDto> createItem(@Valid @RequestBody ItemDto itemDto, @ItemTypeValid @PathVariable(value = "itemType") String itemType) throws Exception {
           
        ResponseDto.ResponseDtoBuilder responseBuilder = ResponseDto.builder();

        // 사용자 정의 Exception 처리 테스트 코드        
        // try {
        //     Integer.parseInt("test");
        // } catch (Exception e) {
        //     throw new ApiException("테스트 에러");
        // }

        itemDto.setItemType(itemType);

        itemService.saveIem(itemDto);     

        // 로깅을 위한 log 변수를 자동으로 생성하여 제공한다.

        log.debug("request item id : {}", itemDto.getId());

        responseBuilder.code("200").message("success");
        
        return ResponseEntity.ok(responseBuilder.build());
        
    }
    

}