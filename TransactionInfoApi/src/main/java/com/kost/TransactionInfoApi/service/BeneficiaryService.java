package com.kost.TransactionInfoApi.service;

import com.kost.TransactionInfoApi.model.Beneficiary;
import com.kost.TransactionInfoApi.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService {
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public Beneficiary getBeneficiary(Long id) {
        return beneficiaryRepository.findById(id).orElse(null);
    }

    // Other methods
}
