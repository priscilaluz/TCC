/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author ADM
 */
public class TccJacksonFeature implements Feature {
    
    /**
     * This method is what actually gets called, when your ResourceConfig
     * registers a Feature.
     * @param context
     * @return 
     */
    @Override
    public boolean configure(FeatureContext context) {

        if (!context.getConfiguration().isRegistered(TccJacksonJaxbJsonProvider.class)) {
            context.register(TccJacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        }
        return true;
    }

    public static class TccJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {

        public TccJacksonJaxbJsonProvider() {
            ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();
            setMapper(mapper);
        }

    }
    
}
