package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.exceptions.BadRequestException;
import com.example.exception.exceptions.FaultyLoginCredentialException;
import com.example.exception.exceptions.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;

// Service annotation tells Spring this is a service layer in our MVC architecture
@Service
public class AccountService {

    AccountRepository accountRepository;

    // All args constructor, AccountRepository dependency injected by Spring
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // Business logic for User Story 1
    // Our API should be able to process new User registrations.
    // Exceptions are thrown if username is empty or proposed credentials do not me user story requirements.
    // Exception definitions and handlers are found in the com.example.exception package.
    // Utilizes custom query method findByUsername defined in AccountRepository.
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

    // Business logic for User Story 2
    // Our API should be able to process User logins.
    // Excpetion is thrown if login attempt fails to match a username and password in the account table.
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
