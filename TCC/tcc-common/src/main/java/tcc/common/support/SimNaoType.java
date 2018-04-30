/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.support;

import org.hibernate.type.CharBooleanType;

public class SimNaoType extends CharBooleanType {
    public static final String TYPE_CLASS = "tcc.common.support.SimNaoType";

    public SimNaoType() {
        super("S".charAt(0), "N".charAt(0));
    }
    
    
    @Override
    public String getName() {
        return "tcc.common.support.SimNaoType";
    }


}
