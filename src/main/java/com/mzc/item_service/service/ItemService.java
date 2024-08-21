package com.mzc.item_service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzc.item_service.domain.Item;
import com.mzc.item_service.dto.DepartmentDto;
import com.mzc.item_service.dto.ItemDto;
import com.mzc.item_service.feign.DepartmentFeignClient;
import com.mzc.item_service.feign.HistoryFeignClient;
import com.mzc.item_service.repository.ItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final DepartmentFeignClient departmentFeignClient;

    private final HistoryFeignClient historyFeignClient;


    public ItemDto saveItem(ItemDto itemDto) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = now.format(formatter);

        Item item = Item.builder()
            .id(itemDto.getId())
            .name(itemDto.getName())
            .description(itemDto.getDescription())
            .count(itemDto.getCount())
            .regDate(date)
            .updDate(date)       
            .accountId(itemDto.getAccountId())    
            .build();

        Item savedItem = itemRepository.save(item);     

        itemDto.setId(savedItem.getId());

        Map<String, Object> historyMap  = new HashMap<>();
        historyMap.put("accountId", savedItem.getAccountId());
        historyMap.put("itemId", savedItem.getId());

        log.info("historyFeignClient = {}", historyFeignClient);

        log.info("feign result = {}", historyFeignClient.createHistory(historyMap));
        
        return itemDto;
           
    }

    public ItemDto findItemById(String id) throws Exception {
        Optional<Item> optional = itemRepository.findById(id);

        if (optional.isPresent()) {
            Item item = optional.get();
            ItemDto itemDto = ItemDto.builder()
                                .id(item.getId())
                                .count(item.getCount())
                                .description(item.getDescription())
                                .name(item.getName())
                                .regDate(item.getRegDate())
                                .updDate(item.getUpdDate())
                                .build();

            ResponseEntity<String> responseEntity = departmentFeignClient.retrieveDepartmentById(1L);
            String body = responseEntity.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jackson2HalModule());

            EntityModel<DepartmentDto> entityModel = objectMapper.readValue(body, new TypeReference<EntityModel<DepartmentDto>>() {});
            DepartmentDto departmentDto = entityModel.getContent();                    
           
            log.info("departmentDto = {}", departmentDto);    
            
            return itemDto;
            
        }

        return null;
    
    }

}
