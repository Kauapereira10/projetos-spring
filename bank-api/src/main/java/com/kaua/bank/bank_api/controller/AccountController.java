package com.kaua.bank.bank_api.controller;

import com.kaua.bank.bank_api.dto.DepositaryAndWithdrawRequest;
import com.kaua.bank.bank_api.dto.TransferRequest;
import com.kaua.bank.bank_api.entity.Account;
import com.kaua.bank.bank_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<Account>> listAccounts() {
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        try {
            Account account1 = service.createAccount(account.getNameHolder(),account.getType());
            return new ResponseEntity<>(account1, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/depositary")
    public ResponseEntity<Void> depositary(@PathVariable Long id, @RequestBody DepositaryAndWithdrawRequest depositaryRequest) {
        try {
            service.depositary(id, depositaryRequest.getValue());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable Long id, @RequestBody DepositaryAndWithdrawRequest withdrawRequest) {
        try {
            service.withdraw(id, withdrawRequest.getValue());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest data) {
        try {
            service.transfer(data.getOriginId(), data.getDestinationId(), data.getValue());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
