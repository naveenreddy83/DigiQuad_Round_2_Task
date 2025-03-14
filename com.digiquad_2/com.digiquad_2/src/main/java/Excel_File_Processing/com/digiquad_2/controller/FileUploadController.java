package Excel_File_Processing.com.digiquad_2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Excel_File_Processing.com.digiquad_2.dto.RowData;
import Excel_File_Processing.com.digiquad_2.service.FileProcessingService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileProcessingService fileProcessingService;

    public FileUploadController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "startRow", defaultValue = "0") int startRow,
        @RequestParam(value="endrow")int endrow)
        {

        try {
            String processedData = fileProcessingService.processFile(file, startRow, endrow);
            return ResponseEntity.ok(processedData);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }
}

