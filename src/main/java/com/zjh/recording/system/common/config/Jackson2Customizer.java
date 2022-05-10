package com.zjh.recording.system.common.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zjh
 * @Description
 * @date 2021/01/20 11:33
 */
@Configuration
public class Jackson2Customizer {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return jacksonObjectMapperBuilder -> {
			//修复前端 js 显示Long类型的id值的精度问题
			jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
		};
	}

}
