package com.kost.TransactionInfoApi.service;

import com.kost.TransactionInfoApi.model.Account;
import com.kost.TransactionInfoApi.model.Beneficiary;
import com.kost.TransactionInfoApi.model.Transaction;
import com.kost.TransactionInfoApi.repository.AccountRepository;
import com.kost.TransactionInfoApi.repository.BeneficiaryRepository;
import com.kost.TransactionInfoApi.repository.TransactionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class DataLoader {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void loadData() throws Exception {
        loadBeneficiaries();
        loadAccounts();
        loadTransactions();
    }

    private void loadBeneficiaries() throws Exception {
        Resource resource = new ClassPathResource("beneficiaries.csv");
        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                Beneficiary beneficiary = new Beneficiary();
                beneficiary.setId(Long.parseLong(csvRecord.get("beneficiaryId")));
                beneficiary.setFirstName(csvRecord.get("firstName"));
                beneficiary.setLastName(csvRecord.get("lastName"));
                beneficiaryRepository.save(beneficiary);
            }
        }
    }

    private void loadAccounts() throws Exception {
        Resource resource = new ClassPathResource("accounts.csv");
        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                Account account = new Account();
                account.setId(Long.parseLong(csvRecord.get("accountId")));
                account.setBeneficiaryId(Long.parseLong(csvRecord.get("beneficiaryId")));
                accountRepository.save(account);
            }
        }
    }

    private void loadTransactions() throws Exception {
        Resource resource = new ClassPathResource("transactions.csv");
        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                Transaction transaction = new Transaction();
                transaction.setId(Long.parseLong(csvRecord.get("transactionId")));
                transaction.setAccountId(Long.parseLong(csvRecord.get("accountId")));
                transaction.setBalance(Double.parseDouble(csvRecord.get("balance")));
                transaction.setType(csvRecord.get("type"));
                transactionRepository.save(transaction);
            }
        }
    }
}