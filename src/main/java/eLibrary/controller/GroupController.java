package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.domain.LessonSubGroup;
import eLibrary.domain.User;
import eLibrary.repos.BookRepo;
import eLibrary.repos.SubGroupRepo;
import eLibrary.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/subGroup")
public class GroupController {

    private final SubGroupRepo subGroupRepo;
    private final BookRepo bookRepo;

    public GroupController(SubGroupRepo subGroupRepo, BookRepo bookRepo) {
        this.subGroupRepo = subGroupRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping("{subGroup}")
    public String subGroupEditForm(
            @PathVariable LessonSubGroup subGroup,
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @AuthenticationPrincipal User user
    ){
        model.addAttribute("subGroup", subGroup);
        model.addAttribute("books", subGroup.getGroupBooks());
        model.addAttribute("myLesson", subGroup.getLesson().getTeacher().getId().equals(user.getId()));

        Iterable<Book> possibleBooks;
        if(filter.isEmpty()){
            possibleBooks = bookRepo.findFiveByRandom();
        } else{
            possibleBooks = bookRepo.findByNameLike(filter);
        }
        model.addAttribute("filter", filter);
        model.addAttribute("possibleBook", possibleBooks);

        return "subGroupEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @PostMapping
    public String subGroupSave(
            @RequestParam String name,
            @RequestParam("subGroupId") LessonSubGroup subGroup,
            Model model
    ){
        if (!StringUtils.isEmpty(name)) {
            subGroup.setName(name);

            subGroupRepo.save(subGroup);
        }else{
            model.addAttribute("nameError","Введите имя");
        }

        return "subGroupEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @GetMapping("/delete/{subGroup}/{book}")
    public String deleteBook(
            Model model,
            @PathVariable LessonSubGroup subGroup,
            @PathVariable Book book
            ){
        subGroup.getGroupBooks().remove(book);
        subGroupRepo.save(subGroup);

        return "redirect:/subGroup/"+subGroup.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @GetMapping("/add/{subGroup}/{book}")
    public String addBook(
            Model model,
            @PathVariable LessonSubGroup subGroup,
            @PathVariable Book book
    ){
        subGroup.getGroupBooks().add(book);
        subGroupRepo.save(subGroup);

        return "redirect:/subGroup/"+subGroup.getId();
    }
}
