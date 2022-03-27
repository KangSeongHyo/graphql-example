package com.example.graphqlexample.bean;

import com.example.graphqlexample.vo.BronzeManager;
import com.example.graphqlexample.vo.SilverManager;
import graphql.kickstart.tools.SchemaParserDictionary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }

    @Bean
    public SchemaParserDictionary schemaParserDictionary(){
        return new SchemaParserDictionary()
                .add(BronzeManager.class)
                .add(SilverManager.class);
    }
}
