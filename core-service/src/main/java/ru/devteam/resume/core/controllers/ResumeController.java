package ru.devteam.resume.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.core.converters.ResumeConverter;
import ru.devteam.resume.core.dtos.CreateNewResumeDto;
import ru.devteam.resume.core.dtos.ResumeFullDto;
import ru.devteam.resume.core.dtos.ResumeShortDto;
import ru.devteam.resume.core.services.FileStorageService;
import ru.devteam.resume.core.services.PdfFileService;
import ru.devteam.resume.core.services.ResumeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name="Резюме", description="")
@RequestMapping("/api/v1/resumes")
public class ResumeController {
    @Value("${storage.path}")
    private String path;
    private final ResumeService resumeService;
    private final ResumeConverter resumeConverter;
    private final PdfFileService pdfFileService;
    private final FileStorageService fileStorageService;

    @Operation(summary = "Получение всех резюме")
    @GetMapping
    public List<ResumeShortDto> getAllResumes() {
        return resumeService.findAll().stream().map(resumeConverter::entityToShortDto).collect(Collectors.toList());
    }

    @Operation(summary = "Получение всех резюме пользователя по его id")
    @GetMapping("user/{userId}")
    public List<ResumeShortDto> getAllResumesByUserId(@PathVariable Long userId){
        return resumeService.findResumesByUserId(userId).stream().map(resumeConverter::entityToShortDto).collect(Collectors.toList());
    }

    @Operation(summary = "Получение развернутого резюме по id")
    @GetMapping("/{id}")
    public ResumeFullDto getResumeById(@PathVariable Long id) {
        return resumeService.getFullResumeById(id);
    }

    @Operation(summary = "Создание резюме")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewResume(@RequestBody CreateNewResumeDto createNewResumeDto) {
        resumeService.createNew(createNewResumeDto);
    }

    @Operation(summary = "Обновление резюме")
    @PutMapping("/update")
    public void updateResume(@RequestBody ResumeShortDto resumeShortDto) {
        resumeService.update(resumeConverter.shortDtoToEntity(resumeShortDto));
    }

    @Operation(summary = "Выгрузка резюме в виде pdf файла")
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
