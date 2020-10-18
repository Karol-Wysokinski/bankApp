package com.bank.bankApp.controller;

import com.bank.bankApp.domain.Account;
import com.bank.bankApp.dto.CreateAccountDto;
import com.bank.bankApp.dto.CurrencyExchangeDto;
import com.bank.bankApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AccountController {

    private final AccountService accountService;
    private final RestTemplate restTemplate;

    @Autowired
    public AccountController(AccountService service, RestTemplate restTemplate) {
        this.accountService = service;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(
            @RequestBody CreateAccountDto dto) {
        Account p = accountService.save(dto);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    //pobranie kursu waluty z NBP
    @GetMapping("/test")
    public String getResponse() {

        String response = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/usd/?format=json", String.class);
        System.out.println(response);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllUsers() {
        List<Account> listOfUsers = accountService.getListOfUserAccounts();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    @GetMapping("/{pesel}")
    public ResponseEntity<Optional<Account>> findUserByPesel(@PathVariable String pesel) {
        Optional<Account> byPesel = accountService.findAccountByPesel(pesel);
        return ResponseEntity.ok(byPesel);
    }

//    @GetMapping("/test1")
//    public BigDecimal currencyExchange(@Valid CurrencyExchangeDto dto) {
//
//        if (dto.getCurrencyFrom().equalsIgnoreCase("PLN")) {
//            String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + dto.getCurrencyTo() +
//                    "?format=json";
//            RateResponse response = restTemplate.getForObject(url, RateResponse.class);
//            BigDecimal rate = BigDecimal.valueOf(response.getRates().get(0).getMid())
//                    .setScale(2, RoundingMode.HALF_UP);
//            return dto.getAmount().divide(rate, RoundingMode.HALF_DOWN);
//        } else {
//            String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + dto.getCurrencyFrom() +
//                    "?format=json";
//            RateResponse response = restTemplate.getForObject(url, RateResponse.class);
//            BigDecimal rate = BigDecimal.valueOf(response.getRates().get(0).getMid())
//                    .setScale(2, RoundingMode.HALF_UP);
//            return dto.getAmount().multiply(rate);
//        }
    }

