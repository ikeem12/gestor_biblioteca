package com.johan.infrastructure.config;

import com.johan.exception.app.UndefinedEnvironmentVariableException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase para la carga centralizada de variables de entorno desde archivos {@code .env}.
 *
 * <p>Esta clase actúa como punto único de acceso a las variables de entorno de la aplicación.
 * Selecciona automáticamente el archivo {@code .env} correspondiente al entorno actual
 * (desarrollo, staging, producción, etc.) definido en {@link Environment}, y utiliza la
 * librería {@code java-dotenv} para su lectura.</p>
 *
 * <p>Responsabilidad principal:</p>
 * <ul>
 *   <li><b>get(String key):</b> Devuelve el valor de la variable de entorno indicada.
 *       Lanza {@link UndefinedEnvironmentVariableException} si la variable no está definida.</li>
 * </ul>
 */
public class Env {
    private static final Dotenv dotenv = Dotenv.configure()
            .filename(".env." + Environment.current().name().toLowerCase())
            .load();

    public static String get(String key){
        String value = dotenv.get(key);
        if (value == null) {
            throw new UndefinedEnvironmentVariableException(key);
        }
        return value;
    }
}