package com.kataexaltit.bankaccount.controller;

import com.kataexaltit.bankaccount.com.bankaccount.models.Operation;
import com.kataexaltit.bankaccount.com.bankaccount.models.dto.AccountDto;
import com.kataexaltit.bankaccount.com.bankaccount.models.dto.OperationCommand;
import com.kataexaltit.bankaccount.service.BankAccountLogicImpl;
import com.kataexaltit.bankaccount.service.OperationsLogicImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts/")
public class BankAccountController {
    private final BankAccountLogicImpl bankAccountLogicImpl;
    private final OperationsLogicImpl operationsLogicImpl;

    public BankAccountController(BankAccountLogicImpl bankAccountLogicImpl, OperationsLogicImpl operationsLogicImpl) {
        this.bankAccountLogicImpl = bankAccountLogicImpl;
        this.operationsLogicImpl = operationsLogicImpl;
    }
    @GetMapping("{accountId}")
    public AccountDto checkAccountState(@PathVariable long accountId) {
        return  bankAccountLogicImpl.checkAccount(accountId);
    }

    @GetMapping("{accountId}/history")
    public ResponseEntity<List<Operation>> showOperationsList(@PathVariable long accountId) {
        return new  ResponseEntity<List<Operation>> (bankAccountLogicImpl.listAllOperations(accountId), HttpStatus.OK);
    }
    @PutMapping(value = "{accountId}/deposit")
    public ResponseEntity<AccountDto> Deposit(@PathVariable long accountId,
                              @RequestBody OperationCommand operationCommand)  {
        return new ResponseEntity<AccountDto> (operationsLogicImpl.DepositMoney(accountId,operationCommand.getAmount()), HttpStatus.OK);
    }
    @PutMapping(value = "{accountId}/withdrawal")
    public ResponseEntity<AccountDto> withdrawal(@PathVariable long accountId,
                                 @RequestBody OperationCommand operationCommand) {
        return new ResponseEntity<AccountDto> (operationsLogicImpl.WithdrawalMoney(accountId,operationCommand.getAmount()), HttpStatus.OK);
    }
}
