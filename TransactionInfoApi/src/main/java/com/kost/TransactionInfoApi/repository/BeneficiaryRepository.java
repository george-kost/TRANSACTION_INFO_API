package com.kost.TransactionInfoApi.repository;

import com.kost.TransactionInfoApi.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
