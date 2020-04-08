package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.domain.Lesson;
import eLibrary.domain.LessonSubGroup;
import eLibrary.domain.User;
import eLibrary.repos.LessonRepo;
import eLibrary.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;

    public LessonController(LessonRepo lessonRepo, UserRepo userRepo) {
        this.lessonRepo = lessonRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/new")
    public String newLesson(
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter
    ){
        Iterable<User> teachers;
        if(!filter.isEmpty()) {
            teachers = userRepo.findTeacherByUsername(filter);
        } else {
            teachers = new HashSet<>();
        }
        model.addAttribute("possibleTeacher",teachers);
        model.addAttribute("filter", filter);
        return "lessonCreate";
    }

    @PostMapping("/new")
    public String saveLesson(
            Model model,
            @RequestParam String name,
            @RequestParam(name = "teacherId") User teacher
    ){
        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setTeacher(teacher);

        lessonRepo.save(lesson);

        return "redirect:/lesson/new";
    }

    @PostMapping("subGroupFilter")
    public String subGroupFilter(
            Model model,
            @RequestParam("subGroupId") LessonSubGroup subGroup,
            @RequestParam String filter
    ){
        String s = "redirect:/lesson/"+subGroup.getId()+"?filter="+filter;
        return s;
    }
}
