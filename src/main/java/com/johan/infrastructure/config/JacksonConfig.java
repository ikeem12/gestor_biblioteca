package com.johan.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Configuración centralizada de Jackson para la aplicación.
 *
 * <p>Proporciona una instancia única de {@link ObjectMapper} preconfigurada
 * con los módulos y ajustes necesarios para la serialización y deserialización JSON.</p>
 *
 * <p>Configuraciones aplicadas:</p>
 * <ul>
 *   <li>Registro del módulo {@link JavaTimeModule} para soporte de tipos de fecha y hora de Java 8+.</li>
 *   <li>Deshabilita {@link SerializationFeature#WRITE_DATES_AS_TIMESTAMPS} para serializar fechas en formato ISO-8601.</li>
 * </ul>
 */
public class JacksonConfig {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static ObjectMapper get() {
        return objectMapper;
    }
}
