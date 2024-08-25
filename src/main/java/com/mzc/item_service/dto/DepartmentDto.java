package com.mzc.item_service.dto;

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
