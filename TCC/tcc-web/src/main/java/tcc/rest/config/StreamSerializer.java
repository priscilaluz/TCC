package tcc.rest.config;

import java.io.InputStream;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class StreamSerializer extends JsonSerializer<InputStream> {

    @Override
    public void serialize(InputStream inputStream, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeBinary(IOUtils.toByteArray(inputStream));
    }

    @Override
    public Class<InputStream> handledType() {
        return InputStream.class;
    }

}