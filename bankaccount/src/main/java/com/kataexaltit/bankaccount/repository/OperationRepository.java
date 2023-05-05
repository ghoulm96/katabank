package com.kataexaltit.bankaccount.repository;

import com.kataexaltit.bankaccount.com.bankaccount.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long>{
}
