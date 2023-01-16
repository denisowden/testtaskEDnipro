package com.journal.demo.web;


import com.journal.demo.service.DownloadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/download")
@RequiredArgsConstructor
@Api(tags = {"Download file"}, description = "You can download converted file")
public class DownloadFileController {
    private final DownloadFileService downloadFileService;

    @ApiOperation(value = "download file", notes = "Download .pdf")
    @GetMapping
    public void downloadPDFResource(HttpServletResponse response) {
        Path file = Paths.get("D:\\Программирование\\Трудоустройство\\Тестовое задание єДніпро\\demo\\src\\main\\resources\\templates\\report.pdf");
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=report.pdf");
        downloadFileService.downloadFile();
        try {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** Добавить headers */
//    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public @ResponseBody byte[] getFile() throws IOException {
//        downloadFileService.dowloadFile();
//        InputStream in = getClass()
//                .getResourceAsStream("/templates/report.pdf");
//        return IOUtils.toByteArray(in);
//    }
}
