package com.johan.infrastructure.db;

import javax.sql.DataSource;

/**
 * Fábrica para la creación de pools de conexiones a bases de datos según el tipo especificado.
 * 
 * <p>Implementa el patrón de diseño Factory para abstraer la creación de 
 * instancias de {@link DataSource} configuradas para diferentes motores 
 * de bases de datos (MySQL, PostgreSQL, etc.).</p>
 * 
 * <p>El objetivo es centralizar la lógica de selección del proveedor, 
 * permitiendo extender el soporte a nuevos motores sin modificar el 
 * código cliente.</p>
 */

public class DataSourceFactory {

    /**
     * Crea un {@link DataSource} según el tipo de base de datos especificado.
     *
     * @param dbType tipo de base de datos (por ejemplo, {@code "mysql"}, {@code "postgres"}).
     * @return instancia de {@link DataSource} configurada para el motor correspondiente.
     * @throws IllegalArgumentException si el tipo de base de datos no está soportado.
     */
    public static DataSource create(String dbType){
        return switch (dbType.toLowerCase()){
            case "mysql" -> new MySQLConnectionPoolFactory().creaDataSource();
            case "postgres" -> new PostgresConnectionPoolFactory().createDataSource();
            case "" -> throw new IllegalArgumentException("La base de datos no fue definida");
            default -> throw new IllegalArgumentException("Base de datos no soportada: " + dbType);
        };
    }
}