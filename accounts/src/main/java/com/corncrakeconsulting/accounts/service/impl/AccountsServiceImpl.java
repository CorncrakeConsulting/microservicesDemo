package com.corncrakeconsulting.accounts.service.impl;

import com.corncrakeconsulting.accounts.constants.AccountsConstants;
import com.corncrakeconsulting.accounts.dto.AccountsDto;
import com.corncrakeconsulting.accounts.dto.CustomerDto;
import com.corncrakeconsulting.accounts.exception.CustomerAlreadyExistsException;
import com.corncrakeconsulting.accounts.exception.ResourceNotFoundException;
import com.corncrakeconsulting.accounts.mapper.AccountsMapper;
import com.corncrakeconsulting.accounts.mapper.CustomerMapper;
import com.corncrakeconsulting.accounts.repository.AccountsRepository;
import com.corncrakeconsulting.accounts.repository.CustomerRepository;
import com.corncrakeconsulting.accounts.service.IAccountsService;
import com.corncrakeconsulting.accounts.entity.Accounts;
import com.corncrakeconsulting.accounts.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private static final String ENTITY_CUSTOMER = "Customer";
    private static final String ENTITY_ACCOUNT = "Account";

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private static final int ACCOUNT_NUMBER_LOWER_BOUND = 1_000_000_000;
    private static final int ACCOUNT_NUMBER_UPPER_BOUND = 2_000_000_000; // Exclusive

    @Override
    public String createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        return accountsRepository.save(createNewAccount(savedCustomer)).getAccountNumber().toString();
    }



    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = ThreadLocalRandom.current().nextLong(ACCOUNT_NUMBER_LOWER_BOUND, ACCOUNT_NUMBER_UPPER_BOUND);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_CUSTOMER, "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_ACCOUNT, "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (accountsDto == null) {
            return false; // No update needed
        }

        Accounts accounts = findAccountOrThrow(accountsDto.getAccountNumber());
        AccountsMapper.mapToAccounts(accountsDto, accounts);
        accountsRepository.save(accounts);

        updateCustomer(accounts.getCustomerId(), customerDto);

        return true;
    }

    private Accounts findAccountOrThrow(Long accountNumber) {
        return accountsRepository.findById(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_ACCOUNT, "AccountNumber", accountNumber.toString()));
    }

    private void updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_CUSTOMER, "CustomerID", customerId.toString()));

        CustomerMapper.mapToCustomer(customerDto, customer);
        customerRepository.save(customer);
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_CUSTOMER, "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}