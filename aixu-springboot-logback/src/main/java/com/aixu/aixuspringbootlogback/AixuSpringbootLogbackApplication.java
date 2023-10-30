package com.aixu.aixuspringbootlogback;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AixuSpringbootLogbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AixuSpringbootLogbackApplication.class,args);
		String id = IdUtil.getSnowflake(1, 1).nextIdStr();

		MDC.put("track_id",id);
		log.info("这是info");
		MDC.remove("track_id");
		log.warn("这是warn");
		MDC.put("track_id",id);
		log.error("这是error");
	}

}
