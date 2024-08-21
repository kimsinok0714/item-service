package com.mzc.item_service.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mzc.item_service.config.FeignClientConfiguration;
import com.mzc.item_service.dto.DepartmentDto;



/**
 * Feign과 API Gateway를 함께 사용하는 이유
 * 1. 중앙 집중화된 트래픽 관리
 * 2. 보안 및 정책 적용
 * 3. 라우팅의 유연성
 * 4. 서비스 디스커버리 통합
 */


 /*
  * item-service-dev.yml
  *  api:
  *     gateway:
  *       url: http://localhost:8070 
  *
  */

@FeignClient(name = "APIGATEWAY-SERVER", url = "${api.gateway.url}", configuration = FeignClientConfiguration.class) 
public interface DepartmentFeignClient {

    @GetMapping("/api/v1/departments/{departmentId}")
    ResponseEntity<String> retrieveDepartmentById(@PathVariable("departmentId") Long departmentId);

  
}

