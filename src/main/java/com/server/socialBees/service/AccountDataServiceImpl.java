package com.server.socialBees.service;

import com.server.socialBees.entity.AccountData;
import com.server.socialBees.repository.AccountDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountDataServiceImpl implements AccountDataService{
    private final AccountDataRepository accountDataRepository;
    public AccountDataServiceImpl(AccountDataRepository accountDataRepository) {
        this.accountDataRepository = accountDataRepository;
    }

    @Override
    @Transactional
    public AccountData createAccountData(AccountData accountData){return accountDataRepository.save(accountData);}

    @Override
    @Transactional
    public AccountData getAccountDataBy(Integer accountDataId){
        return accountDataRepository.findAccountDataById(accountDataId);
    }

    @Override
    @Transactional
    public AccountData updateAccountData(AccountData newAccountData){
        AccountData accountData = accountDataRepository.findAccountDataById(newAccountData.getId());
        if(accountData != null){
            accountData.setAccountNumber(newAccountData.getAccountNumber());
            return accountDataRepository.save(accountData);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public AccountData deleteAccountDataBy(Integer accountDataId){
        AccountData accountData = accountDataRepository.findAccountDataById(accountDataId);
        accountData.setDeleted(true);

        return accountDataRepository.save(accountData);
    }
}
