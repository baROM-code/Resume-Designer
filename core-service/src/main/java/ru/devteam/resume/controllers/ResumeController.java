package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.ResumeConverter;
import ru.devteam.resume.dtos.CreateNewResumeDto;
import ru.devteam.resume.dtos.ResumeFullDto;
import ru.devteam.resume.dtos.ResumeShortDto;
import ru.devteam.resume.services.FileStorageService;
import ru.devteam.resume.services.PdfFileService;
import ru.devteam.resume.services.ResumeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {
    @Value("${storage.path}")
    private String path;
    private final ResumeService resumeService;
    private final ResumeConverter resumeConverter;
    private final PdfFileService pdfFileService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public List<ResumeShortDto> getAllResumes() {
        return resumeService.findAll().stream().map(resumeConverter::entityToShortDto).collect(Collectors.toList());
    }

    @GetMapping("user/{userId}")
    public List<ResumeShortDto> getAllResumesByUserId(@PathVariable Long userId){
        return resumeService.findResumesByUserId(userId).stream().map(resumeConverter::entityToShortDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResumeFullDto getResumeById(@PathVariable Long id) {
        return resumeService.getFullResumeById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewResume(@RequestBody CreateNewResumeDto createNewResumeDto) {
        resumeService.createNew(createNewResumeDto);
    }

    @PutMapping("/update")
    public void updateResume(@RequestBody ResumeShortDto resumeShortDto) {
        resumeService.update(resumeConverter.shortDtoToEntity(resumeShortDto));
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<Resource> genegatePdf(@PathVariable Long id){
        fileStorageService.init(path);
        String filename = pdfFileService.generate(resumeService.getFullResumeById(id));
        Resource file = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\""+file.getFilename()+"\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

}
