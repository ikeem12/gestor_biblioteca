package com.johan.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Clase genérica para representar una respuesta estándar de API.
 * 
 * <p>Incluye un indicador de éxito, un mensaje descriptivo y un objeto de datos
 * de tipo genérico {@code T}. Se utiliza para unificar el formato de las
 * respuestas enviadas desde el servidor al cliente.</p>
 *
 * <p>La anotación {@link com.fasterxml.jackson.annotation.JsonInclude}
 * con {@code Include.NON_NULL} asegura que las propiedades con valor {@code null}
 * no se incluyan en la serialización JSON.</p>
 *
 * @param <T> el tipo de dato que contendrá la respuesta (por ejemplo, una entidad, DTO o lista).
 *
 * <p>Ejemplo de uso:
 * <pre>
 *     ApiResponse<User> response = new ApiResponse<>(
 *         true,
 *         "Usuario encontrado",
 *         userObject
 *     );
 * </pre>
 * </p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private List<String> errors;
    private T  data;

    public ApiResponse(boolean success, String message, List<String> errors, T data){
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public boolean isSuccess() { 
        return success; 
    }

    public void setSuccess(boolean success) { 
        this.success = success; 
    }

    public String getMessage() { 
        return message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }

    public List<String> getErrors(){
        return errors;
    }

    public void setErrors(List<String> errors){
        this.errors = errors;
    }

    public T getData() { 
        return data; 
    }

    public void setData(T data) { 
        this.data = data; 
    }
}