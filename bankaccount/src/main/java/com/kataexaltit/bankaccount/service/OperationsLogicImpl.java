package com.kataexaltit.bankaccount.service;

import com.kataexaltit.bankaccount.BusinessLogic.OperationsLogic;
import com.kataexaltit.bankaccount.com.bankaccount.models.BankAccount;
import com.kataexaltit.bankaccount.com.bankaccount.models.Operation;
import com.kataexaltit.bankaccount.com.bankaccount.models.OperationType;
import com.kataexaltit.bankaccount.error.ApiBaseException;
import com.kataexaltit.bankaccount.error.ConflictException;
import com.kataexaltit.bankaccount.error.ErrorDetails;
import com.kataexaltit.bankaccount.repository.BankAccountRepository;
import com.kataexaltit.bankaccount.com.bankaccount.models.dto.AccountDto;
import com.kataexaltit.bankaccount.repository.OperationRepository;

import jakarta.transaction.Transactional;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class OperationsLogicImpl implements OperationsLogic  {
    private final OperationRepository operationRepository;
    private final BankAccountRepository bankAccountRepository;
    private final AccountDtoMapper dtoMapper;

  @Override
    public AccountDto WithdrawalMoney(long accountId, long amount)  {

        Operation operation = createAndPerformOperation(accountId,amount,OperationType.WITHDRAWAL);
        BankAccount bankAccount = bankAccountRepository.findById(accountId).get();
        bankAccount.getOperations().add(operation);
        return dtoMapper.mapEntityToDto(bankAccount);
    }


    public OperationsLogicImpl(OperationRepository operationRepository, BankAccountRepository bankAccountRepository, AccountDtoMapper dtoMapper) {
        this.operationRepository = operationRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.dtoMapper = dtoMapper;
    }




    @Override
    public AccountDto DepositMoney(long accountId, long amount) {
        Operation operation = createAndPerformOperation(accountId,amount,OperationType.DEPOSIT);
        BankAccount bankAccount = bankAccountRepository.findById(accountId).get();
        bankAccount.getOperations().add(operation);
        return dtoMapper.mapEntityToDto(bankAccount);
    }

    @VisibleForTesting
    Operation createAndPerformOperation(long accountId, long amount, OperationType operationType) throws ConflictException {
            try {
                Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
                BankAccount account = optionalBankAccount.get();
                int opType = operationType.equals(OperationType.WITHDRAWAL) ? -1 : 1;
                Operation operation = new Operation();
                operation.setAmount(opType * amount);
                operation.setDate(Instant.now());
                operation.setAccount(account);
                operation.setType(operationType);
                if (account.balance <= -1000 && opType == -1) {
                    throw new ConflictException("you can no longer withdraw money please contact your advisor");
                }
                account.balance += opType * amount;
                operationRepository.save(operation);
                return operation;
            } catch (NoSuchElementException ex) {
            throw new com.kataexaltit.bankaccount.error.NotFoundException(String.format("No such bank account with number [%s] in database", accountId));
        }
    }
}
