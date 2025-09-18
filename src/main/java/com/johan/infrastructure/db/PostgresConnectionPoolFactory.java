package com.johan.infrastructure.db;

import javax.sql.DataSource;

import com.johan.infrastructure.config.AppConfig;

/**
 * Fábrica para la creación de pools de conexiones para bases de datos PostgreSQL.
 * 
 * <p>Implementa la interfaz {@link ConnectionPool}, utilizando {@code HikariCP} como proveedor 
 * de conexiones. La configuración se obtiene de variables de entorno ({@code POSTGRES_URL}, 
 * {@code POSTGRES_USER}, {@code POSTGRES_PASSWORD}).</p>
 *
 * <p>Devuelve un {@link javax.sql.DataSource} listo para usarse en operaciones 
 * con bases de datos PostgreSQL, sin que el código cliente dependa directamente de HikariCP.</p>
 */

public class PostgresConnectionPoolFactory {

    public DataSource createDataSource() {
        return AppConfig.getDataSource();
    }
}