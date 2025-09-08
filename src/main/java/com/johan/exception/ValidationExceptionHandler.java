package com.johan.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.johan.util.ApiResponseUtil;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionHandler extends ApiResponseUtil implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception){
        List<String> errors = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        return ApiResponseUtil.validationErrorResponse("Error de validacion", errors, Response.Status.BAD_REQUEST);
    }
}