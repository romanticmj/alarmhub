package com.roman.alarm.alarmhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.roman.alarm.alarmhub.remote"})
@MapperScan("com.roman.alarm.alarmhub.mapper")
@EnableCaching
@EnableAsync
public class AlarmHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlarmHubApplication.class, args);
	}

}
