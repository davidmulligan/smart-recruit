package com.eureka.smartrecruit.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

@Configuration
public class DozerConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(new ClassPathResource("dozer_mapper.xml").getPath()));
        return mapper;
    }
}
