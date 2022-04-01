package com.pado.SpringCorePrincipleBasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}) // db 설정하라고 오류나서 일단 제외
public class SpringCorePrincipleBasicApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringCorePrincipleBasicApplication.class, args);
	}

}
