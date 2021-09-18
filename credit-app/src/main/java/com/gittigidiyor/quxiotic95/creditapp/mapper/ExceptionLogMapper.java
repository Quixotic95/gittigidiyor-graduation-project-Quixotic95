package com.gittigidiyor.quxiotic95.creditapp.mapper;

import com.gittigidiyor.quxiotic95.creditapp.dto.ExceptionLogDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.log.ExceptionLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExceptionLogMapper {

    ExceptionLogMapper INSTANCE = Mappers.getMapper(ExceptionLogMapper.class);

    ExceptionLogDTO toExceptionLogDto(ExceptionLog exceptionLog);

}
