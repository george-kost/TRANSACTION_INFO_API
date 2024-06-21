package com.kost.TransactionInfoApi.service;

import com.kost.TransactionInfoApi.model.Account;
import com.kost.TransactionInfoApi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccountsByBeneficiaryId(Long beneficiaryId) {
        return accountRepository.findByBeneficiaryId(beneficiaryId);
    }

    // Other methods
}
