package com.kataexaltit.bankaccount.service;

import com.kataexaltit.bankaccount.com.bankaccount.models.BankAccount;
import com.kataexaltit.bankaccount.com.bankaccount.models.Operation;
import com.kataexaltit.bankaccount.com.bankaccount.models.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class AccountDtoMapper {
    public AccountDto mapEntityToDto(BankAccount account){
        AccountDto dto = new AccountDto();
        dto.setBalance(account.getBalance());
        List<Operation> recentOps = account.getOperations()
                .stream()
                .sorted(Comparator.comparing(Operation::getDate).reversed())
                .limit(5).collect(Collectors.toList());
        dto.setLatestOperations(recentOps);
        return dto;
    }
}
