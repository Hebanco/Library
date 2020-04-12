package eLibrary.controller;

import eLibrary.domain.Lesson;
import eLibrary.domain.LessonSubGroup;
import eLibrary.domain.User;
import eLibrary.repos.LessonRepo;
import eLibrary.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final LessonRepo lessonRepo;

    public LessonController(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
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

//    @GetMapping("/{lesson}/addSubGroup")
//    public String newSubGroup(
//            Model model,
//            @PathVariable Lesson lesson
//            ){
//        LessonSubGroup subGroup = new LessonSubGroup();
//        lesson.getSubGroups().add(subGroup);
//        lessonRepo.save(lesson);
//
//        return "redirect:/subGroup/"+subGroup.getId();
//    }

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
