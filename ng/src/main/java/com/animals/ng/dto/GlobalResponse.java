package com.animals.ng.dto;

public record GlobalResponse(
        String status,
        String message,
         Object data) {
}
