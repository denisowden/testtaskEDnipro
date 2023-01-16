package com.journal.demo.mapper;

import com.journal.demo.entity.Student;
import com.journal.demo.web.dto.StudentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapper {
    StudentDto toDto(Student student);
}
