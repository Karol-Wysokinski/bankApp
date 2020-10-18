package com.bank.bankApp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class CreateAccountDto {

    //    @Size(min = 11, max = 11, message = "PESEL number must have 11 numbers!")
//    @NotNull
    @PESEL(message = "Please input correct PESEL")
    private String pesel;

    @NotNull(message = "Please input correct name")
    private String name;

    @NotNull(message = "Please input correct surname")
    private String surname;

    @NotNull
    @Min(value = 18, message = "You must be an adult to create an account!")
    private Integer age;

    private BigDecimal accountBalance;


}
