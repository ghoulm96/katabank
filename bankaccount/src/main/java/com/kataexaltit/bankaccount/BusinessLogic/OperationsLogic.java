package com.kataexaltit.bankaccount.BusinessLogic;

import com.kataexaltit.bankaccount.com.bankaccount.models.dto.AccountDto;

public interface OperationsLogic {
    public AccountDto WithdrawalMoney(long accountId, long amount) ;
    public AccountDto DepositMoney(long accountId, long amount);
}
