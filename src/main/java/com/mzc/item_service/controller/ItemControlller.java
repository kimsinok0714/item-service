package com.mzc.item_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mzc.item_service.dto.ItemDto;
import com.mzc.item_service.exception.ApiException;
import com.mzc.item_service.service.ItemService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



// @EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "물품 서비스 요청 API", description = "물품 서비스 요청 API 입니다.", version = "v1"))
@RestController
@RequestMapping(value = "api/v1/items")
@Slf4j
@RequiredArgsConstructor
@Validated
@RefreshScope
public class ItemControlller {

    private final ItemService itemService;

    @Autowired
    private HttpServletRequest request;

    
    @Operation(summary = "물품 등록 요청", description = "물품 등록 요청을 처리한다.", tags = {"createItem"})  
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "SUCCESS"),
        @ApiResponse(responseCode = "501", description = "ApiException")
    })       
    @PostMapping
    public EntityModel<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) throws Exception {

        String accountId = request.getHeader("accountId").toString().replace("[", "").replace("]", "");
        
        log.info("accountId = {}", accountId);

        itemDto.setAccountId(accountId);
           
        ItemDto savedItemDto = itemService.saveItem(itemDto);

        // 로깅을 위한 log 변수를 자동으로 생성하여 제공한다.

        log.debug("saved item id : {}", savedItemDto.getId());      

        EntityModel<ItemDto> entityModel = EntityModel.of(savedItemDto);

        entityModel.add(linkTo(methodOn(ItemControlller.class).retrieveItemById(savedItemDto.getId())).withSelfRel());;
        
        return entityModel;
        
    }
    
    
    @Operation(summary = "아이템 상세 조회  요청", description = "아이템 상세 조회 요청을 처리한다.", tags = {"retrieveItemById"})  
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "SUCCESS"),
        @ApiResponse(responseCode = "500", description = "ApiException")
    })   
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)     // application/hal+json
    public EntityModel<ItemDto> retrieveItemById(@PathVariable(value = "id") String id) throws Exception {

        //EntityModel은 데이터 또는 객체에 추가적인 하이퍼미디어 링크를 제공하기 위해서 사용된다.
        log.info("item id = {}", id);

        ItemDto itemDto = itemService.findItemById(id);
        if (itemDto == null) {
            throw new ApiException("There is no item associated with the provided id");
        }
        
        EntityModel<ItemDto> entityModel = EntityModel.of(itemDto);

        entityModel.add(linkTo(methodOn(ItemControlller.class).createItem(itemDto)).withRel("create-item"));        
        
        return entityModel;

    }

}