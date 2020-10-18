package com.bank.bankApp.service;

import com.bank.bankApp.domain.Account;
import com.bank.bankApp.dto.CreateAccountDto;
import com.bank.bankApp.exception.AccountWithPeselExists;
import com.bank.bankApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.accountRepository = repository;
    }

    public Account save(CreateAccountDto dto) {
        assertAccountWithGivenPeselDoesNotExist(dto.getPesel());
        Account account = Account.builder()
                .pesel(dto.getPesel())
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .creationTime(LocalDateTime.now())
                .build();

        accountRepository.save(account);

        return account;

    }

    public List<Account> getListOfUserAccounts() {
        List<Account> all = accountRepository.findAll();
        return all;
    }

    public Optional<Account> findAccountByPesel(String pesel) {
        Optional<Account> accountByPesel = accountRepository.findAccountByPesel(pesel);
        return accountByPesel;

    }

    private void assertAccountWithGivenPeselDoesNotExist(String pesel) {
        boolean exist = AccountRepository.existsAccountByPesel(pesel);
        if (exist) {
            throw new AccountWithPeselExists("Account with given pesel already exists!");
        }

    }


}
