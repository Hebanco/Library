package eLibrary.controller;

import eLibrary.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

@Controller
public class ReportController {

    public final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/lessons")
    public String createReportLessons(
            Model model
    ) throws IOException {
        File file = reportService.reportLessonWithTeacher();
        model.addAttribute("fileName", file.getName());
        model.addAttribute("path", file.getAbsolutePath());
        return "reportInformation";
    }

    @GetMapping("/report/books")
    public String createReportBooks(
            Model model
    ) throws IOException {
        File file = reportService.reportAllBook();
        model.addAttribute("fileName", file.getName());
        model.addAttribute("path", file.getAbsolutePath());
        return "reportInformation";
    }

    @GetMapping("/report/files")
    public String createReportFiles(
            Model model
    ) throws IOException {
        File file = reportService.reportAllFiles();
        model.addAttribute("fileName", file.getName());
        model.addAttribute("path", file.getAbsolutePath());
        return "reportInformation";
    }

}
