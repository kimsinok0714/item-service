package com.mzc.item_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;

/*
 * Eureka Server에 등록된 history-service인 마이크로 서비스로 요청하겠다는 의미이다. 

 */
@FeignClient(name = "history-service")
public interface HistoryFeignClient {

    @PostMapping(value = "api/v1/histories/create")
    String createHistory(Map<String, Object> paramMap);

}
