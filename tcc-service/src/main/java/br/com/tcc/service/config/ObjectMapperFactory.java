/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.config;

import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.Jogos;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.util.TimeZone;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author Priscila
 */
public class ObjectMapperFactory {

    public static ObjectMapper createObjectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule testModule = new SimpleModule("TccModule", new Version(1, 0, 0, null, null, null));
            // Inclusao dos Custom Deserializer para Enums
            testModule.addDeserializer(Categoria.class, new EnumDeserializer(ReflectionUtils.findMethod(Categoria.class, "from", String.class)));
            testModule.addDeserializer(Jogos.class, new EnumDeserializer(ReflectionUtils.findMethod(Jogos.class, "from", String.class)));
            mapper.registerModule(testModule);
            mapper.registerModule(new Hibernate4Module());
            mapper.setTimeZone(TimeZone.getDefault());
            return mapper;
    }

}
