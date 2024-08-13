package com.mzc.item_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String id;
    private String name;
    private String description;
    private long count;
    private String regDate;
    private String upDate;

}