package com.kaua.bank.bank_api.service;

import com.kaua.bank.bank_api.entity.Account;
import com.kaua.bank.bank_api.enums.AccountType;
import com.kaua.bank.bank_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public List<Account> findAll() throws Exception {
        List<Account> accounts = repository.findAll();

        if (accounts.isEmpty()) {
            throw new Exception("Não tem contas.");
        }
        return accounts;
    }

    @Transactional
    public Account createAccount(String nameHolder, AccountType type) throws Exception {

        Account account = new Account();
        account.setNameHolder(nameHolder);
        account.setType(type);
        account.setBalance(0.0);

        return repository.save(account);
    }

    @Transactional
    public void depositar(Long id, double value) throws Exception {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if(value > 0) {
            account.setBalance(account.getBalance() + value);
            repository.save(account);
        } else {
            throw new IllegalArgumentException("Valor tem que ser maior que zero.");
        }
    }

    @Transactional
    public double sacar(Long id, double value) throws Exception {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if(value > 0 && value <= account.getBalance()) {
            account.setBalance(account.getBalance() - value);
            repository.save(account);
        } else {
            throw new IllegalArgumentException("Valor tem que ser maior que zero ou o valor tem que ser menor que o saldo dad conta.");
        }

        return value;
    }

    @Transactional
    public void transferir(Long idOrigem, Long idDestino, double value) throws Exception {
        Account account1 = repository.findById(idOrigem).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        Account account2 = repository.findById(idDestino).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if(!idOrigem.equals(idDestino)) {
            double valueAccount1 = this.sacar(idOrigem, value);
            this.depositar(idDestino, valueAccount1);
        } else {
            throw new IllegalArgumentException("A conta tem que ser diferente.");
        }
    }

}
