package com.example.graphqlexample.bean;

import org.hibernate.collection.spi.PersistentCollection;
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
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));

        return modelMapper;
    }

//    @Bean
//    public SchemaParserDictionary schemaParserDictionary(){
//        return new SchemaParserDictionary()
//                .add(BronzeManager.class)
//                .add(SilverManager.class);
//    }
}
