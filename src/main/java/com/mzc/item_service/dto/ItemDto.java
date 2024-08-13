package com.mzc.item_service.dto;

import com.mzc.item_service.dto.constant.ItemType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * @Data
 * 1. @Getter
 * 2. @Setter
 * 3. @ToString
 * 4. @EqualsAndHashCode
 * 5. @RequiredArgsConstructor : final, @NonNull 필드를 대상으로 생성자 메소드를 생성한다.
 */

@Data
public class ItemDto {
    @NotBlank(message = "ID is a required field.")
    @Size(max = 10, message = "ID can be up to 10 characters long.")
    private String id;   
    private String name;
    private String description;
    @Positive(message = "Only positive numbers are allowed.")
    private long count;
    private String regDate;
    private String upDate;
    private String itemType;
}