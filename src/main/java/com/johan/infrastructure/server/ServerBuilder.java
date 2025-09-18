package com.johan.infrastructure.server;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import com.johan.infrastructure.config.JacksonConfig;

/***
 * <h4> Builder para configurar y crear un servidor HTTP con Grizzly y Jersey. </h4>
 * 
 * <p>Esta clase implementa el patrón Builder, permitiendo construir instancias del servidor
 * de forma flexible y declarativa mediante una API fluida.</p>
 * 
 * <h4>Funcionalidades principales:</h4>
 * <ul>
 *   <li>Configuración de la URI base del servidor.</li>
 *   <li>Registrar paquetes de recursos (controllers/endpoints).</li>
 *   <li>Configurar validación de peticiones (validación por defecto o personalizada).</li>
 *   <li>Habilitar serialización/deserialización JSON con Jackson (soporte para fechas).</li>
 *  <li>Asociar un DataSource mediante un Factory, permitiendo soportar múltiples motores de base de datos.</li>
 * </ul>
 * 
 * <h4>Ejemplo de uso:</h4>
 * <pre>
 *   HttpServer server = new ServerBuilder()
 *       .withBaseUri("http://localhost:8080/api")
 *       .withResourcePackages("com.johan.controller")
 *       .withDefaultValidation()
 *       .withJacksonMapper()
 *       .withDataSource("mysql")
 *       .build();
 * </pre>
 */
public class ServerBuilder {
    private String baseUri;
    private ResourceConfig resourceConfig;

    public ServerBuilder() {
        this.resourceConfig = new ResourceConfig();
    }

    public ServerBuilder withBaseUri(String baseUri){
        this.baseUri = baseUri;
        return this;
    }

    public ServerBuilder withResourcePackages(String... packages) {
        this.resourceConfig.packages(packages);
        return this;
    }

    public ServerBuilder withDefaultValidation(){
        this.resourceConfig.register(org.glassfish.jersey.server.validation.ValidationFeature.class);
        return this;
    }

    public ServerBuilder withCustomValidation(Class<?> validationProvider) {
        this.resourceConfig.register(validationProvider);
        return this;
    }

    public ServerBuilder withJacksonMapper() {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(JacksonConfig.get());

        this.resourceConfig.register(provider);
        return this;
    }

    public HttpServer build() {
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), resourceConfig);
    }
}