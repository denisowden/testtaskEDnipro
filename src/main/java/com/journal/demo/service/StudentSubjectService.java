package com.journal.demo.service;

import com.journal.demo.entity.StudentSubject;
import com.journal.demo.mapper.StudentSubjectMapperImpl;
import com.journal.demo.repository.StudentRepository;
import com.journal.demo.repository.StudentSubjectRepository;
import com.journal.demo.repository.SubjectRepository;
import com.journal.demo.web.dto.StudentSubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSubjectService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentSubjectRepository studentSubjectRepository;
    private final StudentSubjectMapperImpl studentSubjectMapperImpl;

    public StudentSubject addMark(StudentSubject studentSubject) {
        if(studentRepository.findById(studentSubject.getStudentId()).isPresent() &&
            subjectRepository.findById(studentSubject.getSubjectId()).isPresent() &&
            studentSubject.getMark() <=12 &&
            studentSubject.getMark() > 0) studentSubjectRepository.save(studentSubject);

        return studentSubject;
    }

    public List<StudentSubjectDto> getFilter(String student, String subject, Integer mark) {
        if (subject != null && mark != null) {
            return studentSubjectRepository.getStudentWithMarkOver(subject, mark).stream().map(studentSubjectMapperImpl::toDto).toList();
        } else if (student != null) {
            return studentSubjectRepository.getMarksByStudent(student).stream().map(studentSubjectMapperImpl::toDto).toList();
        } else if (mark != null) {
            return studentSubjectRepository.getStudentsWithAverageMarkBelow(mark).stream().map(studentSubjectMapperImpl::toDtoStudentsWithAverageMarkBelow).toList();
        }
            return studentSubjectRepository.getAllJournal().stream().map(studentSubjectMapperImpl::toDto).toList();
    }
}
