/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest.config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author ADM
 */
public class TccResourceConfig extends ResourceConfig {

    public TccResourceConfig() {
        register(TccJacksonFeature.class);
        register(MultiPartFeature.class);
        packages("br.com.tcc.rest");
    }
}
