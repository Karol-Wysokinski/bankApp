package com.bank.bankApp.repository;

import com.bank.bankApp.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository {

    boolean existsAccountByPesel(String pesel);

    Optional<Account> findAccountByPesel(String pesel);
}
