package com.johan;

import io.github.cdimascio.dotenv.Dotenv;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class App 
{
    public static HttpServer startServer(){
        Dotenv dotenv = Dotenv.load();
        String baseUri = dotenv.get("BASE_URI");

        final ResourceConfig rc = new ResourceConfig().packages("com.johan.controller");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
    }

    public static void main( String[] args )
    {
        startServer();
    }
}
