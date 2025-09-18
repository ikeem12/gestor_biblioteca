package com.johan.infrastructure.db;

import javax.sql.DataSource;

/**
 *  Interfaz para la creación de pools de conexiones a bases de datos.
 *  
 *  </p>Define el contrato que deben implementar las fábricas de pools de conexiones,
 *  garantizando que cada implementación proporcione un {@link DataSource}
 *  correctamente configurado para un motor de base de datos específico.</p>
 * 
 *  <p>El uso de {@link DataSource} se debe porque al ser  al ser una interfaz estándar de  
 *  JDBC, permite que las fábricas utilicen cualquier implementación de pool de conexiones 
 *  (HikariCP, C3P0, Tomcat JDBC, etc.) sin que el código cliente dependa de una librería 
 *  concreta.</p>
 * 
 * @see javax.sql.DataSource
 */
public interface ConnectionPool {
    DataSource creaDataSource();
}