package ru.devteam.resume.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.devteam.resume.core.services.ResumeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mini")
public class MiniSiteController {
    private final ResumeService resumeService;

    @GetMapping("/{id}")
    public ModelAndView getProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("mini");
        modelAndView.addObject("resume", resumeService.getFullResumeById(id));
        return modelAndView;
    }

}
