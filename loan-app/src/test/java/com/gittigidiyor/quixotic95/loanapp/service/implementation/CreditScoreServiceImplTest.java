package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.dto.CreditScoreDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.CreditScore;
import com.gittigidiyor.quixotic95.loanapp.mapper.CreditScoreMapper;
import com.gittigidiyor.quixotic95.loanapp.repository.CreditScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditScoreServiceImplTest {

    @Mock
    CreditScoreRepository creditScoreRepository;

    @Mock
    CreditScoreMapper creditScoreMapper;

    @InjectMocks
    CreditScoreServiceImpl creditScoreService;

    @Test
    void should_return_CreditScoreDTO_when_generateCreditScoreByTckn_runs() {

        CreditScore creditScore = new CreditScore();
        creditScore.setCreditScore(800.0);
        CreditScoreDTO creditScoreDTO = new CreditScoreDTO();
        creditScoreDTO.setCreditScore(800.0);

        when(creditScoreRepository.findCreditScoreByCustomerTckn(anyString())).thenReturn(Optional.of(creditScore));
        when(creditScoreMapper.toCreditScoreDto(creditScore)).thenReturn(creditScoreDTO);

        CreditScoreDTO expected = creditScoreMapper.toCreditScoreDto(creditScore);

        CreditScoreDTO actual = creditScoreService.generateCreditScoreByTckn(anyString());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> verify(creditScoreMapper).toCreditScoreDto(creditScore)
        );

    }
}