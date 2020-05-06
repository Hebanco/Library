package eLibrary.controller;

import eLibrary.domain.Lesson;
import eLibrary.domain.LessonSubGroup;
import eLibrary.domain.User;
import eLibrary.repos.LessonRepo;
import eLibrary.repos.SubGroupRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final LessonRepo lessonRepo;
    private final SubGroupRepo subGroupRepo;

    public LessonController(LessonRepo lessonRepo, SubGroupRepo subGroupRepo) {
        this.lessonRepo = lessonRepo;
        this.subGroupRepo = subGroupRepo;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
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
            @Valid Lesson lesson,
            BindingResult bindingResult,
            Model model,
            @RequestParam(name = "teacherId") User teacher
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("teacher", teacher);
            return "lessonCreate";
        }
        lesson.setTeacher(teacher);

        lessonRepo.save(lesson);

        model.addAttribute("teacher", teacher);
        return "lessonCreate";
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

    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @GetMapping("/myLessons")
    public String userLesson(
            Model model,
            @AuthenticationPrincipal User user
    ){
        model.addAttribute("lessons", lessonRepo.findByTeacher(user));
        return "lessonList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @PostMapping("/{lesson}")
    public String newSubGroup(
            @Valid LessonSubGroup subGroup,
            BindingResult bindingResult,
            Model model,
            @PathVariable Lesson lesson
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("lesson", lesson);
            model.addAttribute("subGroups", lesson.getSubGroups());
            return "lessonEdit";
        }
        subGroupRepo.save(subGroup);

        lesson.getSubGroups().add(subGroup);
        lessonRepo.save(lesson);
        return "redirect:/lesson/"+lesson.getId();
    }

    @GetMapping("{lesson}")
    public String lessonEdit(
            Model model,
            @PathVariable Lesson lesson,
            @AuthenticationPrincipal User user
    ){
        model.addAttribute("lesson", lesson);
        model.addAttribute("subGroups", lesson.getSubGroups());
        model.addAttribute("myLesson", user.getId().equals(lesson.getTeacher().getId()));

        return "lessonEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
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
