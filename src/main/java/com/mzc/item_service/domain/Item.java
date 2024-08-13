package com.mzc.item_service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String id;
    private String name;
    private String description;
    private long count;
    private String regDate;
    private String upDate;
}


