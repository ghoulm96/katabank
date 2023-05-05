package com.kataexaltit.bankaccount.service;

import com.kataexaltit.bankaccount.BusinessLogic.BankAccountLogic;
import com.kataexaltit.bankaccount.com.bankaccount.models.BankAccount;
import com.kataexaltit.bankaccount.com.bankaccount.models.Operation;
import com.kataexaltit.bankaccount.com.bankaccount.models.dto.AccountDto;
import com.kataexaltit.bankaccount.repository.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class BankAccountLogicImpl implements BankAccountLogic {
    private final BankAccountRepository bankAccountRepository;
    private final AccountDtoMapper accountDtoMapper;

    public BankAccountLogicImpl(BankAccountRepository bankAccountRepository, AccountDtoMapper accountDtoMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountDtoMapper = accountDtoMapper;
    }

    /**
     *
     * @param accountId account identifier
     * @return all operations on a given account
     */
    @Override
    public List<Operation> listAllOperations(long accountId) {
        try {
            Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
            return optionalBankAccount.get().operations;
        } catch (NoSuchElementException ex) {
            throw new com.kataexaltit.bankaccount.error.NotFoundException(String.format("No such bank account with number [%s] in database", accountId));
        }

    }

    @Override
    public AccountDto checkAccount(long accountId) {
        try {
            Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
            return accountDtoMapper.mapEntityToDto(optionalBankAccount.get());
        }  catch (NoSuchElementException ex) {
            throw new com.kataexaltit.bankaccount.error.NotFoundException(String.format("No such bank account with number [%s] in database", accountId));
        }

    }


}
