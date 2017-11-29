/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest.config;

import br.com.tcc.common.enums.CategoriaEnum;
import br.com.tcc.common.enums.Jogo;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.common.enums.TipoUsuario;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;
import java.io.InputStream;
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
            testModule.addDeserializer(CategoriaEnum.class, new EnumDeserializer(ReflectionUtils.findMethod(CategoriaEnum.class, "from", String.class)));
            testModule.addDeserializer(Jogo.class, new EnumDeserializer(ReflectionUtils.findMethod(Jogo.class, "from", String.class)));
            testModule.addDeserializer(SituacaoCurso.class, new EnumDeserializer(ReflectionUtils.findMethod(SituacaoCurso.class, "from", String.class)));
            testModule.addDeserializer(NivelPergunta.class, new EnumDeserializer(ReflectionUtils.findMethod(NivelPergunta.class, "from", String.class)));
            testModule.addDeserializer(TipoPergunta.class, new EnumDeserializer(ReflectionUtils.findMethod(TipoPergunta.class, "from", String.class)));
            testModule.addDeserializer(TipoUsuario.class, new EnumDeserializer(ReflectionUtils.findMethod(TipoUsuario.class, "from", String.class)));
            testModule.addDeserializer(InputStream.class, new StreamDeserializer());
            testModule.addSerializer(new StreamSerializer());
            mapper.registerModule(testModule);
            Hibernate4Module hm = new Hibernate4Module();
            hm.disable(Feature.USE_TRANSIENT_ANNOTATION);
            mapper.registerModule(hm);
            mapper.setTimeZone(TimeZone.getDefault());
            return mapper;
    }

}
