package com.journal.demo.web;

import com.journal.demo.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload")
@RequiredArgsConstructor
@Api(tags = {"Upload file"}, description = "You can upload file to convert")
public class UploadFileController {

    private final UploadFileService uploadFileService;

    @ApiOperation(value = "post file", notes = "Upload .xls or .xlsx")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file){
        try {
            uploadFileService.writeInDb(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping
//    public List<StudentSubject> uloadFile(){
//        try {
//            return uploadFileService.writeInDb(null);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
