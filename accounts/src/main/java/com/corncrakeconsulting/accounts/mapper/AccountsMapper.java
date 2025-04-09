package com.corncrakeconsulting.accounts.mapper;

import com.corncrakeconsulting.accounts.dto.AccountsDto;
import com.corncrakeconsulting.accounts.entity.Accounts;

public class AccountsMapper {
    private AccountsMapper() {
        // Private constructor to prevent instantiation
    }
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static void mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
    }

}