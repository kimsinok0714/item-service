package com.mzc.item_service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.mzc.item_service.domain.Item;
import com.mzc.item_service.dto.ItemDto;
import com.mzc.item_service.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public void saveIem(ItemDto itemDto) {

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
            .itemType(itemDto.getItemType())
            .build();

        itemRepository.save(item);     
           
    }

}
