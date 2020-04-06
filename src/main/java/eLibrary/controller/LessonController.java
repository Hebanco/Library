package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.domain.Lesson;
import eLibrary.domain.LessonSubGroup;
import eLibrary.domain.User;
import eLibrary.repos.LessonRepo;
import eLibrary.repos.SubGroupRepo;
import eLibrary.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LessonController {

    private final LessonRepo lessonRepo;


    public LessonController(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @GetMapping("newLesson")
    public String newLesson(Model model){
        return "lessonCreate";
    }

    @PostMapping("newLesson")
    public String saveLesson(
            Model model,
            @RequestParam String name,
            @RequestParam(name = "teacherId") User teacher
    ){
        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setTeacher(teacher);

        lessonRepo.save(lesson);

        return "redirect:/newLesson";
    }
}
