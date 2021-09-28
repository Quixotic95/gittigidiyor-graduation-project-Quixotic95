package com.gittigidiyor.quixotic95.loanapp.mapper;

import com.gittigidiyor.quixotic95.loanapp.dto.LoanApplicationResultDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanApplicationResultMapper {

    LoanApplicationResultMapper INSTANCE = Mappers.getMapper(LoanApplicationResultMapper.class);

    LoanApplicationResultDTO toLoanApplicationResultDto (LoanApplicationResult loanApplicationResult);

}
