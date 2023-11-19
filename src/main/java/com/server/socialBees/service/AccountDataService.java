package com.server.socialBees.service;

import com.server.socialBees.entity.AccountData;

public interface AccountDataService {
    AccountData createAccountData(AccountData accountData);
    AccountData getAccountDataBy(Integer userId);
    AccountData updateAccountData(AccountData accountData);
    AccountData deleteAccountDataBy(Integer userId);
}
