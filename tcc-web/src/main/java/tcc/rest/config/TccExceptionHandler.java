/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest.config;

import tcc.common.exception.BusinessException;
import tcc.common.exception.PendingException;
import tcc.common.exception.Pendency;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;


/**
 *
 * @author kbos
 */
@Provider
public class TccExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Throwable cleanException = cleanException(exception);
        LoggerFactory.getLogger(getClass()).error(cleanException.getMessage(), cleanException);
        String message = cleanException.getMessage();
        
        if(cleanException instanceof BusinessException) {
            StringBuilder builder = new StringBuilder();
            for (Pendency pendency : ((PendingException)cleanException).getPending()) {
                builder.append(MessageFormat.format(getBundleMessage(pendency.getMessage()), (pendency.getAdditionalData())));
                builder.append(System.getProperty("line.separator"));
                builder.append(System.getProperty("line.separator"));
            }
            message = builder.toString();
        } else if (cleanException instanceof PendingException) {
            message = MessageFormat.format(getBundleMessage(message), ((BusinessException)cleanException).getMessageParams());
            //message = StringEscapeUtils.escapeHtml3(builder.toString());
        }
        
        message = message == null ? "null" : message;
        
        Map m = new HashMap();
        m.put("message", message);
        m.put("st", ExceptionUtils.getFullStackTrace(cleanException));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(m)
                       .type("application/json").build();
        
        //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).type(MediaType.TEXT_HTML).build();
    }

    private String getBundleMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("tcc-messages");
        String message = key;
        if (bundle.containsKey(key)) {
            message = bundle.getString(key);
        }
        return message;
    }
    
    public static Throwable cleanException(Exception t) {
        Throwable ex = t;
        while (ex != null && ex.getCause() != null && ((ex instanceof InvocationTargetException)
                || (ex instanceof UndeclaredThrowableException)
                || (ex instanceof ExecutionException) 
                || (ex instanceof JsonMappingException))) {
            ex = ex.getCause();
        }
        return ex;
    }

}
