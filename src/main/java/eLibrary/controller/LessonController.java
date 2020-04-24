package eLibrary.controller;

import eLibrary.domain.Lesson;
import eLibrary.domain.LessonSubGroup;
import eLibrary.domain.User;
import eLibrary.repos.LessonRepo;
import eLibrary.repos.SubGroupRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final LessonRepo lessonRepo;
    private final SubGroupRepo subGroupRepo;

    public LessonController(LessonRepo lessonRepo, SubGroupRepo subGroupRepo) {
        this.lessonRepo = lessonRepo;
        this.subGroupRepo = subGroupRepo;
    }

    @GetMapping("/new")
    public String newLesson(
            Model model,
            @RequestParam(name = "teacherId"/*, required = false, defaultValue = ""*/) User teacher
    ){
        model.addAttribute("teacher", teacher);
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

        return "redirect:/user/"+teacher.getId();
    }

    @GetMapping("/list/{teacher}")
    public String teacherLesson(
            Model model,
            @PathVariable User teacher
    ){
        model.addAttribute("lessons",lessonRepo.findByTeacher(teacher));
        return "lessonList";
    }

    @GetMapping("/list")
    public String allLesson(Model model){
        model.addAttribute("lessons", lessonRepo.findAll());
        return "lessonList";
    }

    @GetMapping("/myLessons")
    public String userLesson(
            Model model,
            @AuthenticationPrincipal User user
    ){
        model.addAttribute("lessons", lessonRepo.findByTeacher(user));
        return "lessonList";
    }

    @PostMapping("/{lesson}/addSubGroup")
    public String newSubGroup(
            Model model,
            @PathVariable Lesson lesson,
            @RequestParam String name
    ){
        LessonSubGroup subGroup = new LessonSubGroup(name);
        subGroupRepo.save(subGroup);

        lesson.getSubGroups().add(subGroup);
        lessonRepo.save(lesson);
        return "redirect:/lesson/"+lesson.getId();
    }

    @GetMapping("{lesson}")
    public String lessonEdit(
            Model model,
            @PathVariable Lesson lesson
    ){
        model.addAttribute("lesson", lesson);
        model.addAttribute("subGroups", lesson.getSubGroups());

        return "lessonEdit";
    }

    @GetMapping("/{lesson}/delete/{subGroup}")
    public String deleteSubGroup(
            Model model,
            @PathVariable Lesson lesson,
            @PathVariable LessonSubGroup subGroup
    ){
        lesson.getSubGroups().remove(subGroup);
        lessonRepo.save(lesson);

        return "redirect:/lesson/"+lesson.getId();
    }
}
