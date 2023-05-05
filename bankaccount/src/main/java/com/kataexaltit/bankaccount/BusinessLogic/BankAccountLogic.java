package com.kataexaltit.bankaccount.BusinessLogic;

import com.kataexaltit.bankaccount.com.bankaccount.models.Operation;
import com.kataexaltit.bankaccount.com.bankaccount.models.dto.AccountDto;

import java.util.List;

public interface BankAccountLogic {
    public List<Operation> listAllOperations(long accountId);
    public AccountDto checkAccount(long accountId);
}
