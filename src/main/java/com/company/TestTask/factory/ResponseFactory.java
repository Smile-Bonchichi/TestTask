package com.company.TestTask.factory;

import com.company.TestTask.dto.response.Response;
import com.company.TestTask.enums.Result;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseFactory {
    final Response response = new Response();

    public Response getInvalidDetailsResponse() {
        return Response.builder()
                .code(Result.INVALID_DETAILS.getErrorId())
                .description(Result.INVALID_DETAILS.getDescription())
                .build();
    }

    public Response getServiceNotAvailableResponse() {
        return Response.builder()
                .code(Result.SERVICE_NOT_AVAILABLE.getErrorId())
                .description(Result.SERVICE_NOT_AVAILABLE.getDescription())
                .build();
    }

    public Response getSupplierErrorResponse() {
        return Response.builder()
                .code(Result.SUPPLIER_ERROR.getErrorId())
                .description(Result.SUPPLIER_ERROR.getDescription())
                .build();
    }

    public Response getNotHaveAmountOnAccountResponse() {
        return Response.builder()
                .code(Result.AMOUNT_GREATER_ZERO.getErrorId())
                .description(Result.AMOUNT_GREATER_ZERO.getDescription())
                .build();
    }
}
