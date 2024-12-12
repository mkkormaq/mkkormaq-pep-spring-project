package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.exceptions.BadRequestException;
import com.example.exception.exceptions.FaultyLoginCredentialException;
import com.example.exception.exceptions.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account){

        String username = account.getUsername();
        String password = account.getPassword();

        if(accountRepository.findByUsername(username) != null){
            throw new UsernameAlreadyExistsException("Username " +username + " is already taken. Please try another username");
        } else if (username.length() > 0 && password.length() >= 4){
            accountRepository.save(account);
            return accountRepository.findByUsername(username);
        }
        throw new BadRequestException("Credentials do not meet requirements. Make sure Username is not empty and Password is at least 4 characters.");
    }

    public Account login(Account account){

        String username = account.getUsername();
        String password = account.getPassword();

        Account storedAccount = accountRepository.findByUsername(username);

        if (storedAccount != null && storedAccount.getPassword().equals(password)){
            return storedAccount;
        }
        throw new FaultyLoginCredentialException("Incorrect Username or Password. Please Try again.");
    }
}
