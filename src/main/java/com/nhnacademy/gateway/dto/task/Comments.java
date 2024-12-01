package com.nhnacademy.gateway.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Comments {
    private Long id;
    private String content;
    private String author;
    private String date;
}
