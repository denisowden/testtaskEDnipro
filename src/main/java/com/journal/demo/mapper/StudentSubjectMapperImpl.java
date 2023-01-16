package com.journal.demo.mapper;

import com.journal.demo.entity.StudentSubject;
import com.journal.demo.web.dto.StudentSubjectDto;
import com.journal.demo.repository.StudentRepository;
import com.journal.demo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentSubjectMapperImpl implements StudentSubjectMapper{

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public StudentSubjectDto toDto(StudentSubject studentSubject) {
        if ( studentSubject == null ) {
            return null;
        }
        StudentSubjectDto studentSubjectDto = new StudentSubjectDto();
        studentSubjectDto.setMark(Double.valueOf(studentSubject.getMark()));
        studentSubjectDto.setNameStudent(
                studentRepository
                        .findById(studentSubject
                                .getStudentId())
                        .get()
                        .getNameStudent()
        );
        studentSubjectDto.setNameSubject(
                subjectRepository
                        .findById(studentSubject
                                .getSubjectId())
                        .get()
                        .getNameSubject()
        );
        return studentSubjectDto;
    }

    public StudentSubjectDto toDtoStudentsWithAverageMarkBelow(Object[] objects) {
        if ( objects[0] == null || objects[1] == null) {
            return null;
        }

        StudentSubjectDto studentSubjectDto = new StudentSubjectDto();
        studentSubjectDto.setNameStudent(String.valueOf(objects[0]));
        studentSubjectDto.setMark(Double.valueOf(String.valueOf(objects[1])));
        return studentSubjectDto;
    }
}
