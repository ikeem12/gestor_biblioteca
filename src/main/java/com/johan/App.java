package com.johan;

import com.johan.exception.ValidationExceptionHandler;

import io.github.cdimascio.dotenv.Dotenv;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;

public class App 
{
    public static HttpServer startServer(){
        Dotenv dotenv = Dotenv.load();
        String baseUri = dotenv.get("BASE_URI");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        final ResourceConfig rc = new ResourceConfig();
        
        rc.packages("com.johan.controller");

        rc.register(org.glassfish.jersey.server.validation.ValidationFeature.class);

        rc.register(provider);

        rc.register(ValidationExceptionHandler.class); 

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
    }

    public static void main( String[] args )
    {
        startServer();
    }
}
