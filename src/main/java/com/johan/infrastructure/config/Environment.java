package com.johan.infrastructure.config;

/**
 * Enum que representa los entornos de ejecución de la aplicación.
 *
 * <p>Los valores posibles son:</p>
 * <ul>
 *   <li>{@code DEVELOPMENT}: Entorno de desarrollo (valor por defecto si no se define {@code APP_ENV}).</li>
 *   <li>{@code STAGING}: Entorno de pruebas o preproducción.</li>
 *   <li>{@code PRODUCTION}: Entorno de producción.</li>
 * </ul>
 *
 * <p>Método principal:</p>
 * <ul>
 *   <li><b>current():</b> Devuelve el entorno actual de ejecución según la variable de entorno
 *   {@code APP_ENV}. Si no está definida, se asume {@code DEVELOPMENT}. Si el valor es inválido,
 *   se lanza una {@link RuntimeException} indicando los valores permitidos.</li>
 * </ul>
 */
public enum Environment{
    DEVELOPMENT, STAGING, PRODUCTION;

    public static Environment current(){
        String env = System.getenv("APP_ENV");
        
        if(env == null) return DEVELOPMENT;

        try {
            return Environment.valueOf(env.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("APP_ENV inválido: " + env + ". Valores válidos: DEVELOPMENT, STAGING, PRODUCTION");
        }
    }
}