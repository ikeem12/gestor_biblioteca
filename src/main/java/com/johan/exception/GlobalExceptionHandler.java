package com.johan.exception;

import com.johan.util.ApiResponseUtil;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Manejador global de excepciones para la aplicación.
 *
 * <p>Implementa {@link jakarta.ws.rs.ext.ExceptionMapper} para interceptar y
 * procesar tanto excepciones esperadas como no esperadas, devolviendo una
 * respuesta estandarizada en formato JSON mediante {@link ApiResponseUtil}.</p>
 *
 * <p>Casos manejados:</p>
 * <ul>
 *   <li>{@link BookNotAvailable}: devuelve un estado HTTP 409 (Conflict) con un mensaje descriptivo.</li>
 *   <li>Otras excepciones: devuelve un estado HTTP 500 (Internal Server Error) con un mensaje genérico.</li>
 * </ul>
 *
 * <p>La clase extiende {@link ApiResponseUtil} para reutilizar métodos de
 * construcción de respuestas de error.</p>
 *
 * @see jakarta.ws.rs.ext.ExceptionMapper
 * @see ApiResponseUtil
 */

@Provider
public class GlobalExceptionHandler extends ApiResponseUtil implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e){
        if (e instanceof BookNotAvailable) {
            return ApiResponseUtil.errorResponse("el libro no esta disponible", Response.Status.CONFLICT);
        }

        if (e instanceof BooksNotAvailableExecption) {
            return ApiResponseUtil.errorResponse("No hay libros disponibles actualmente", Response.Status.OK);
        }

        if (e instanceof BooksNotLentExeception) {
            return ApiResponseUtil.errorResponse("No hay libros prestados actualmente", Response.Status.OK);
        }

        // aqui iria los logs para atrapar las excecpiones mas tecnicas para los desarrolladores

        return ApiResponseUtil.errorResponse("Error interno del servidor", Response.Status.INTERNAL_SERVER_ERROR);
    }
}