package com.journal.demo.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvarageMarkQueryDto {
    private String nameStudent;
    private BigDecimal mark;
}
