package com.psy.csv;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    Mapper getDTOMapper() {
        return new DozerBeanMapper();
    }

}
