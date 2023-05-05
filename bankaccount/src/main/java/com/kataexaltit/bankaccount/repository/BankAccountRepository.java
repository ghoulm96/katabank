package com.kataexaltit.bankaccount.repository;

import com.kataexaltit.bankaccount.com.bankaccount.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

}
