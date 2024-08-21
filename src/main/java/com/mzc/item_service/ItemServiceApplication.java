package com.mzc.item_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;


/*
 * OpenFeign : 넷플릭스에서 개발된 Http Client 기능을 제공하기 위한 라이브러리입니다.
 * 애노테이션을 사용하여 쉽게 HTTP 통신을 할 수 있다. 
*/

@EnableFeignClients     
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)        
@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

}
