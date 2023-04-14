package com.project.cafe_management_system.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseGeneric<T> implements Serializable {
    private int statusCode;
    private String message;
    private T data;

}
