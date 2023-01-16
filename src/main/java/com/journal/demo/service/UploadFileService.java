package com.journal.demo.service;

import com.journal.demo.entity.Student;
import com.journal.demo.entity.StudentSubject;
import com.journal.demo.entity.Subject;
import com.journal.demo.repository.StudentRepository;
import com.journal.demo.repository.StudentSubjectRepository;
import com.journal.demo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UploadFileService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    public List<StudentSubject> writeInDb(MultipartFile file) throws Exception {
        String extension = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename()
                        .lastIndexOf("."));

        Workbook workbook;
        if(extension.equals(".xlsx")) {
            workbook= new XSSFWorkbook(file.getInputStream());
        }else {
            workbook = new HSSFWorkbook((file.getInputStream()));
        }

        List<StudentSubject> listOfStudentSubject = new ArrayList<>();
        Sheet sheet1 = workbook.getSheetAt(0);
        int i = 1;
        while (sheet1.getRow(i) != null) {
            StudentSubject studentSubject = new StudentSubject();
            Row row = sheet1.getRow(i);
            for (Cell cell : row) {
                if(cell.getColumnIndex() == 0) {
                    Student byNameStudent = studentRepository.findByNameStudent(
                            cell.getRichStringCellValue().getString()).orElse(new Student());
                    studentSubject.setStudentId(byNameStudent.getId());
                }else {
                    StudentSubject clone = (StudentSubject) studentSubject.clone();

                    Cell cell1 = sheet1.getRow(0).getCell(cell.getColumnIndex());
                    Subject bySubjectName = subjectRepository.findByNameSubject(
                            cell1.getRichStringCellValue().getString()).orElse(new Subject());
                    clone.setSubjectId(bySubjectName.getId());
                    clone.setMark((int) cell.getNumericCellValue());

                    studentSubjectRepository.save(clone);
                    listOfStudentSubject.add(clone);
                }
            }
            i++;
        }
        return listOfStudentSubject;
    }
}
