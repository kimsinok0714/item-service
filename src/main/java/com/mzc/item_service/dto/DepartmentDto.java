package com.mzc.item_service.dto;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto  {  
   
    private Long departmentId;   
   
    private String departmentName;


}
