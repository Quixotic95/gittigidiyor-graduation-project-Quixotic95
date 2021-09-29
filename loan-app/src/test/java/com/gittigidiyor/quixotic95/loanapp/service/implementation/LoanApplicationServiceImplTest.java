package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.entity.CreditScore;
import com.gittigidiyor.quixotic95.loanapp.entity.Customer;
import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import com.gittigidiyor.quixotic95.loanapp.exception.LoanApplicationAlreadyExistsWithMonthlyIncomeForCustomerException;
import com.gittigidiyor.quixotic95.loanapp.repository.CreditScoreRepository;
import com.gittigidiyor.quixotic95.loanapp.repository.CustomerRepository;
import com.gittigidiyor.quixotic95.loanapp.repository.LoanApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceImplTest {

    @Mock
    LoanApplicationRepository loanApplicationRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CreditScoreRepository creditScoreRepository;

    @InjectMocks
    LoanApplicationServiceImpl loanApplicationService;

    @Test
    void applyForLoanByTckn() {

        Customer customer = new Customer();
        customer.setMonthlyIncome(8000.0);
        CreditScore creditScore = new CreditScore();
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setCustomerMonthlyIncome(8000.0);
        List<LoanApplicationResult> loanApplicationResultList = new ArrayList<>();
        loanApplicationResultList.add(loanApplicationResult);

        when(customerRepository.findCustomerByTckn(anyString())).thenReturn(Optional.of(customer));
        when(creditScoreRepository.findCreditScoreByCustomerTckn(anyString())).thenReturn(Optional.of(creditScore));
        when(loanApplicationRepository.findLoanApplicationResultsByCustomerTckn(anyString())).thenReturn(Optional.of(loanApplicationResultList));

        assertThrows(LoanApplicationAlreadyExistsWithMonthlyIncomeForCustomerException.class, () -> loanApplicationService.applyForLoanByTckn(anyString()));

    }
}