package com.journal.demo.web;

import com.journal.demo.entity.StudentSubject;
import com.journal.demo.service.StudentSubjectService;
import com.journal.demo.web.dto.StudentSubjectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/students")
@Api(tags = {"Entity filter"}, description = "You can filter by params")
public class StudentSubjectController {
    private final StudentSubjectService studentSubjectService;

    @ApiOperation(value = "add student", notes = "You can manually add student and marks")
    @PostMapping
    public StudentSubject addMark(@RequestBody StudentSubject studentSubject){
        return studentSubjectService.addMark(studentSubject);
    }

    @ApiOperation(value = "get filter", notes = "Use this resource to receive filtered data")
    @GetMapping
    public List<StudentSubjectDto> getFilter(
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "student", required = false) String student,
            @RequestParam(value = "mark", required = false) Integer mark
    ){
        return studentSubjectService.getFilter(student, subject, mark);

    }
}
