package com.mzc.item_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/*
 * @Data
 * 1. @Getter
 * 2. @Setter
 * 3. @ToString
 * 4. @EqualsAndHashCode
 * 5. @RequiredArgsConstructor : final, @NonNull 필드를 대상으로 생성자 메소드를 생성한다.
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    
    @Schema(description = "물품ID", example = "TESTID")
    @NotBlank(message = "ID is a required field.")
    @Size(max = 10, message = "ID can be up to 10 characters long.")
    private String id;  

    @Schema(description = "물품명", example = "과일")
    private String name;
    
    @Schema(description = "물품설명", example = "물품설명테스트")
    private String description;
    
    @Schema(description = "물품개수", example = "100")
    @Positive(message = "Only positive numbers are allowed.")
    private long count;
    
    @Schema(hidden= true)
    private String regDate;    
    
    @Schema(hidden= true)
    private String updDate;

    private String accountId;
  
}