package com.johan.util;

import java.util.List;

import jakarta.ws.rs.core.Response;

/**
 * Clase abstracta utilitaria para construir respuestas HTTP estandarizadas.
 * <p>
 * Proporciona métodos estáticos para generar respuestas exitosas o con error
 * utilizando la clase {@link ApiResponse}.
 * </p>
 */
public abstract class ApiResponseUtil {
    
    /**
     * Crea una respuesta HTTP exitosa con datos y mensaje personalizado.
     *
     * @param message mensaje descriptivo del resultado.
     * @param data datos asociados a la respuesta.
     * @param <T> tipo de los datos.
     * @return objeto {@link Response} con estado 200 OK.
     */
    public static <T> Response successResponse(String message, T data) {
        return Response.ok(new ApiResponse<>(true, message, null, data)).build();
    }

    /**
     * Crea una respuesta HTTP de error con mensaje y estado personalizado.
     *
     * @param message mensaje descriptivo del error.
     * @param status estado HTTP que representa el tipo de error.
     * @return objeto {@link Response} con el estado indicado.
     */
    public static Response errorResponse(String message, Response.Status status) {
        return Response.status(status).entity(new ApiResponse<>(false, message, null,null)).build();
    }

    /**
     * Crea una respuesta HTTP de error de validación con mensajes de error detallados.
     * @param message mensaje general del error de validación
     * @param errors lista de mensajes específicos de validación
     * @return objeto {@link Response} con el estado indicado.
     */
    public static Response validationErrorResponse(String message, List<String> errors, Response.Status status) {
        return Response.status(status).entity(new ApiResponse<>(false, message, errors, null)).build();
    }
}