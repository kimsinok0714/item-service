package com.mzc.item_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @Column(name = "id", length = 30)
    private String id;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "description", length = 30)
    private String description;
    @Column(name = "count", length = 30)
    private long count;
    @Column(name = "reg_date", length = 15)
    private String regDate;
    @Column(name = "upd_date", length = 15)
    private String updDate;
    private String itemType;
}


