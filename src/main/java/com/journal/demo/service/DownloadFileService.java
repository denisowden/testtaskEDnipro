package com.journal.demo.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.journal.demo.mapper.StudentSubjectMapperImpl;
import com.journal.demo.web.dto.StudentSubjectDto;
import com.journal.demo.repository.StudentRepository;
import com.journal.demo.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DownloadFileService {

    private final StudentSubjectRepository studentSubjectRepository;
    private final StudentSubjectMapperImpl studentSubjectMapperImpl;
    private final StudentRepository studentRepository;

    public void downloadFile() {

        List<StudentSubjectDto> allJournal = studentSubjectRepository
                .getAllJournal()
                .stream()
                .map(studentSubjectMapperImpl::toDto)
                .toList();

        String fileName = "src/main/resources/templates/report.pdf";

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ){
            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            PdfPTable table = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("Name"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("English"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Math"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("PE"));
            table.addCell(c1);

            table.setHeaderRows(1);

            for(int i = 0; i < allJournal.size(); i = i + 3){
                table.addCell(allJournal.get(i).getNameStudent());
                table.addCell(String.valueOf(allJournal.get(i).getMark()));
                table.addCell(String.valueOf(allJournal.get(i + 1).getMark()));
                table.addCell(String.valueOf(allJournal.get(i + 2).getMark()));
            }

            document.add(table);
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
