package com.bank.bankApp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Rates {

    private String no;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate effectiveDate;

    private double mid;


}
