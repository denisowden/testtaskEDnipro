package com.journal.demo.mapper;

import com.journal.demo.entity.Subject;
import com.journal.demo.web.dto.SubjectDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);
}
