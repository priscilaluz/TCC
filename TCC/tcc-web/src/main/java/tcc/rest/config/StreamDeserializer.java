package tcc.rest.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class StreamDeserializer<E> extends JsonDeserializer<InputStream> {

    public StreamDeserializer() {
    }
    @Override
    public InputStream deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return new ByteArrayInputStream(jsonParser.readValueAs(byte[].class));
    }

}