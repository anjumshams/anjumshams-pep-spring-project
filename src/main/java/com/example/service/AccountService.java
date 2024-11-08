package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {
    AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

     //1. Process new User registrations.
     public Account addUserAccount(Account account){
        return accountRepository.save(account);
    }

     //2. Process User logins.
     public Account UserLogin(Account account){
        String username = account.getUsername();
        String password =  account.getPassword();
        if(username==null||password==null||password.length()<4){
            return null;
        }
        return accountRepository.findByUsernameAndPassword(username, password);
     }




}
