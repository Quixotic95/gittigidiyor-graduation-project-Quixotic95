package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.dto.LoanApplicationResultDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import com.gittigidiyor.quixotic95.loanapp.entity.CreditScore;
import com.gittigidiyor.quixotic95.loanapp.entity.Customer;
import com.gittigidiyor.quixotic95.loanapp.entity.Sms;
import com.gittigidiyor.quixotic95.loanapp.exception.LoanApplicationAlreadyExistsWithMonthlyIncomeForCustomerException;
import com.gittigidiyor.quixotic95.loanapp.mapper.LoanApplicationResultMapper;
import com.gittigidiyor.quixotic95.loanapp.repository.LoanApplicationRepository;
import com.gittigidiyor.quixotic95.loanapp.repository.CreditScoreRepository;
import com.gittigidiyor.quixotic95.loanapp.repository.CustomerRepository;
import com.gittigidiyor.quixotic95.loanapp.repository.SmsRepository;
import com.gittigidiyor.quixotic95.loanapp.service.LoanApplicationService;
import com.gittigidiyor.quixotic95.loanapp.utility.LoanLimitCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;
    private final CreditScoreRepository creditScoreRepository;

    @Transactional
    @Override
    public LoanApplicationResultDTO applyForLoan(String tckn) {

        log.info("inside LoanApplicationServiceImpl applyForLoan");

        Customer foundCustomer = customerRepository.findCustomerByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Customer with TCKN: " + tckn + " couldn't found!"));

        CreditScore foundCreditScore = creditScoreRepository.findCreditScoreByCustomerTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Credit score for Customer with tckn: " + tckn + " not found!"));

        Optional<List<LoanApplicationResult>> existingLoanApplicationResult = loanApplicationRepository.findLoanApplicationResultsByCustomerTckn(tckn);

        if (existingLoanApplicationResult.isPresent()) {
            for (LoanApplicationResult result : existingLoanApplicationResult.get()) {
                if (result.getCustomerMonthlyIncome() == foundCustomer.getMonthlyIncome()) {
                    throw new LoanApplicationAlreadyExistsWithMonthlyIncomeForCustomerException("Customer with TCKN: " + tckn +
                            " already have a loan application result with current monthly income!" +
                            " Please update the monthly income of the customer before applying for a new loan OR" +
                            " inquiry applications to see the result.");
                }
            }
        }

        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();

        loanApplicationResult.setCustomerTckn(tckn);
        loanApplicationResult.setCustomerFirstName(foundCustomer.getFirstName());
        loanApplicationResult.setCustomerLastName(foundCustomer.getLastName());
        loanApplicationResult.setCustomerPhoneNumber(foundCustomer.getPhoneNumber());
        loanApplicationResult.setCustomerMonthlyIncome(foundCustomer.getMonthlyIncome());
        double creditLimit = LoanLimitCalculator.calculateLoanLimit(foundCustomer.getMonthlyIncome(), foundCreditScore.getCreditScore());
        loanApplicationResult.setCreditLimit(creditLimit);
        boolean isAccepted = creditLimit != 0;
        loanApplicationResult.setApproved(isAccepted);

        return LoanApplicationResultMapper.INSTANCE.toLoanApplicationResultDto(loanApplicationRepository.save(loanApplicationResult));
    }

    @Override
    public List<LoanApplicationResultDTO> findLoanApplications(String tckn) {

        log.info("inside LoanApplicationServiceImpl findLoanApplications");

        List<LoanApplicationResult> loanApplicationResults = loanApplicationRepository.findLoanApplicationResultsByCustomerTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Loan Application for customer with TCKN: " + tckn + " could not be found!"));

        return loanApplicationResults
                .stream()
                .map(LoanApplicationResultMapper.INSTANCE::toLoanApplicationResultDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanApplicationResultDTO findLastLoanApplicationResultOfCustomerByTckn(String tckn) {

        log.info("inside LoanApplicationServiceImpl findLastLoanApplicationResultOfCustomerByTckn");

        LoanApplicationResult loanApplicationResult = loanApplicationRepository.findTopLoanApplicationResultByCustomerTcknOrderByCreatedDateDesc(tckn)
                .orElseThrow(() -> new EntityNotFoundException("An error occurred while trying to get last loan application!"));
        return LoanApplicationResultMapper.INSTANCE.toLoanApplicationResultDto(loanApplicationResult);
    }

}
