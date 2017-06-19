package br.com.tcc.service.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;
import org.springframework.util.ReflectionUtils;

/**
 * Custom Deserializer para Enums. A anotacao @JsonFormat(shape =
 * JsonFormat.Shape.OBJECT) nos Enums transformam os enums no formato de Object
 * {"id":"valor","descricao":"valor"} Essa classe realiza o parse do JSON para
 * recuperar o id e utiliza o factory method para recuperar o Enum
 * correspondente. Cada Enum deve registrar o EnumDeserializer informando o
 * factory method na classe LiresJacksonFeature.
 */
public class EnumDeserializer<E> extends JsonDeserializer<E> {

    private final Method method;

    public EnumDeserializer(Method method) {
        this.method = method;
    }

    @Override
    public E deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String enumId = null;
        int nivel = 1;
        do {
            JsonToken token = jp.nextToken();
            if (token == JsonToken.START_OBJECT) {
                nivel++;
            } else if (token == JsonToken.END_OBJECT) {
                nivel--;
            } else {
                String fieldName = jp.getCurrentName();
                if (fieldName.equals("id") && nivel == 1) {
                    enumId = jp.getText();
                }
            }
        } while (nivel > 0);

        return (E) ReflectionUtils.invokeMethod(method, null, enumId);
    }

}
