package com.gittigidiyor.quixotic95.loanapp.mapper;

import com.gittigidiyor.quixotic95.loanapp.dto.CreditScoreDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.CreditScore;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditScoreMapper {

    CreditScoreMapper INSTANCE = Mappers.getMapper(CreditScoreMapper.class);

    CreditScoreDTO toCreditScoreDto(CreditScore creditScore);

}
