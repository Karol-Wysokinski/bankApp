package com.bank.bankApp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Account {

//    @PESEL
    private String pesel;

    private String name;

    private String surname;

    private Integer age;

    private LocalDateTime creationTime;

}
