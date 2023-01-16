package com.journal.demo.mapper;

import com.journal.demo.entity.StudentSubject;
import com.journal.demo.web.dto.StudentSubjectDto;


public interface StudentSubjectMapper {

    StudentSubjectDto toDto(StudentSubject studentSubject);
}
