package com.johan.config;

import javax.sql.DataSource;

import com.johan.exception.app.MissingEnvironmentVariableException;
import com.johan.infrastructure.db.DataSourceFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Clase de configuración centralizada de la aplicación.
 *
 * <p>Sus responsabilidades principales son:</p>
 * <ul>
 *   <li>Cargar y validar las variables de entorno críticas al inicio.</li>
 *   <li>Configurar y exponer el pool de conexiones de base de datos (HikariCP).</li>
 *   <li>Proporcionar la URI base del servidor.</li>
 *   <li>Instanciar el {@link DataSource} correspondiente al motor de base de datos configurado.</li>
 * </ul>
 */
public class AppConfig {
    private static final HikariDataSource pooldataSource;
    private static final DataSource dataSourceFactory;
    private static final String baseUri;

    static {
        checkCriticalEnv("DB_USER", "DB_PASSWORD", "DB_URL", "DB_TYPE", "BASE_URI");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Env.get("DB_URL"));
        config.setUsername(Env.get("DB_USER"));
        config.setPassword(Env.get("DB_PASSWORD"));
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);

        pooldataSource = new HikariDataSource(config);
        dataSourceFactory = DataSourceFactory.create(Env.get("DB_TYPE"));
        baseUri = Env.get("BASE_URI");
    }

    private static void checkCriticalEnv(String... keys) {
        for (String key : keys) {
            String value = Env.get(key); // esto lanzará IllegalStateException si falta
            if (value == null || value.isBlank()) {
                throw new MissingEnvironmentVariableException(key);
            }
        }
    }

    public static DataSource getDataSource() { return pooldataSource; }

    public static DataSource getDataSourceFactory() { return dataSourceFactory;}

    public static String getBaseUri() { return baseUri; }
}