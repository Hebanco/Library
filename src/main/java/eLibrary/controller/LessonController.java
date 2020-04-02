package eLibrary.controller;

import eLibrary.domain.Lesson;
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
    private final SubGroupRepo subGroupRepo;
    private final UserRepo userRepo;

    public LessonController(LessonRepo lessonRepo, SubGroupRepo subGroupRepo, UserRepo userRepo) {
        this.lessonRepo = lessonRepo;
        this.subGroupRepo = subGroupRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("newLesson")
    public String newLesson(Model model){
        return "lessonProfile";
    }

    @PostMapping("newLesson")
    public String saveLesson(
            Model model,
            @RequestParam String name,
            @RequestParam User teacherId
    ){
        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setTeacher(teacherId);

        lessonRepo.save(lesson);

        return "redirect:/newLesson";
    }
}
