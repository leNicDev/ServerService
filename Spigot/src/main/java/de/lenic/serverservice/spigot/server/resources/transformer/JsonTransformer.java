package de.lenic.serverservice.spigot.server.resources.transformer;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

import java.io.IOException;

public class JsonTransformer implements ResponseTransformer {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);


    @Override
    public String render(Object model) throws Exception {
        return objectMapper.writeValueAsString(model);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

}
