package com.mzc.item_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mzc.item_service.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    
}