package com.gittigidiyor.quixotic95.loanapp.mapper;

import com.gittigidiyor.quixotic95.loanapp.dto.ExceptionLogDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.log.ExceptionLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExceptionLogMapper {

    ExceptionLogMapper INSTANCE = Mappers.getMapper(ExceptionLogMapper.class);

    ExceptionLogDTO toExceptionLogDto(ExceptionLog exceptionLog);

}
