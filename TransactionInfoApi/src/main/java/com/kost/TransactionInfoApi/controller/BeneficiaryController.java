package com.kost.TransactionInfoApi.controller;

import com.kost.TransactionInfoApi.model.Account;
import com.kost.TransactionInfoApi.model.Beneficiary;
import com.kost.TransactionInfoApi.model.Transaction;
import com.kost.TransactionInfoApi.service.AccountService;
import com.kost.TransactionInfoApi.service.BeneficiaryService;
import com.kost.TransactionInfoApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
public class BeneficiaryController {
    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public Beneficiary getBeneficiary(@PathVariable Long id) {
        return beneficiaryService.getBeneficiary(id);
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getAccounts(@PathVariable Long id) {
        return accountService.getAccountsByBeneficiaryId(id);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id) {
        List<Account> accounts = accountService.getAccountsByBeneficiaryId(id);
        List<Transaction> transactions = new ArrayList<>();
        for (Account account : accounts) {
            transactions.addAll(transactionService.getTransactionsByAccountId(account.getId()));
        }
        return transactions;
    }

    @GetMapping("/{id}/balance")
    public Double getBalance(@PathVariable Long id) {
        List<Account> accounts = accountService.getAccountsByBeneficiaryId(id);
        double balance = 0;
        for (Account account : accounts) {
            List<Transaction> transactions = transactionService.getTransactionsByAccountId(account.getId());
            for (Transaction transaction : transactions) {
                balance += transaction.getType().equals("deposit") ? transaction.getBalance() : -transaction.getBalance();
            }
        }
        return balance;
    }

    @GetMapping("/{id}/largest-withdrawal")
    public Transaction getLargestWithdrawal(@PathVariable Long id) {
        List<Account> accounts = accountService.getAccountsByBeneficiaryId(id);
        Transaction largestWithdrawal = null;
        for (Account account : accounts) {
            List<Transaction> transactions = transactionService.getTransactionsByAccountId(account.getId());
            for (Transaction transaction : transactions) {
                if (transaction.getType().equals("withdrawal")) {
                    if (largestWithdrawal == null || transaction.getBalance() > largestWithdrawal.getBalance()) {
                        largestWithdrawal = transaction;
                    }
                }
            }
        }
        return largestWithdrawal;
    }
}
