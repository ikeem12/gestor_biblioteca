package com.johan.infrastructure.server;

import java.net.URI;

import javax.sql.DataSource;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.johan.infrastructure.db.DataSourceFactory;

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
    private ObjectMapper objectMapper;
    private DataSource dataSource;

    public ServerBuilder withBaseUri(String baseUri){
        this.baseUri = baseUri;
        return this;
    }

    public ServerBuilder withResourcePackages(String... packages) {
        this.resourceConfig = new ResourceConfig().packages(packages);
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
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(this.objectMapper);

        this.resourceConfig.register(provider);
        return this;
    }

    public ServerBuilder withDataSource(String dbType){
        this.dataSource = DataSourceFactory.create(dbType);
        return this;
    }

    public DataSource getDataSource(){
        return this.dataSource;
    }

    public HttpServer build() {
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), resourceConfig);
    }
}