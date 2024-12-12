package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

//Though adding repository isn't strictly necessary, it's still helpful to spring boot and could prevent issues on larger projects
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    // Utilizing Spring Boot's query method feature to define a search in account table by username.
    // This query is needed for the AccountService method addAccount, as we want to ensure the account doesn't already exist before we're given the accountId.

    public Account findByUsername(String username);
}
