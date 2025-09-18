package com.johan.infrastructure.db;

import javax.sql.DataSource;

import com.johan.infrastructure.config.AppConfig;


/**
 * Fábrica para la creación de pools de conexiones para bases de datos MySQL.
 * 
 * <p>Implementa la interfaz {@link ConnectionPool}, utilizando {@code HikariCP} como proveedor 
 * de conexiones. La configuración se obtiene de variables de entorno ({@code MYSQL_URL}, 
 * {@code MYSQL_USER}, {@code MYSQL_PASSWORD}).</p>
 *
 * <p>Devuelve un {@link javax.sql.DataSource} listo para usarse en operaciones 
 * con bases de datos MySQL, sin que el código cliente dependa directamente de HikariCP.</p>
 */
public class MySQLConnectionPoolFactory implements ConnectionPool{
    @Override
    public DataSource creaDataSource(){
        return AppConfig.getDataSource();
    }
}