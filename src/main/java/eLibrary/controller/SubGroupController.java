package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.domain.SubGroup;
import eLibrary.domain.User;
import eLibrary.service.SubGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subGroup")
public class SubGroupController {

    private final SubGroupService subGroupService;

    public SubGroupController(SubGroupService subGroupService) {
        this.subGroupService = subGroupService;
    }

    @GetMapping("{subGroup}")
    public String subGroupEditForm(
            @PathVariable SubGroup subGroup,
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @AuthenticationPrincipal User user
    ){
        model.addAttribute("subGroup", subGroup);
        model.addAttribute("books", subGroup.getGroupBooks());
        model.addAttribute("myLesson", subGroup.getLesson().getTeacher().getId().equals(user.getId()));

        Iterable<Book> possibleBooks = subGroupService.getBooks(filter);
        model.addAttribute("filter", filter);
        model.addAttribute("possibleBook", possibleBooks);

        return "subGroupEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','LESSON_OVERSEER')")
    @PostMapping
    public String subGroupSave(
            @RequestParam String name,
            @RequestParam("subGroupId") SubGroup subGroup,
            Model model
    ){
        if (!StringUtils.isEmpty(name)) {
            subGroupService.renameSubGroup(name, subGroup);
        }else{
            model.addAttribute("nameError","Введите имя");
        }

        return "subGroupEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','LESSON_OVERSEER')")
    @GetMapping("/delete/{subGroup}/{book}")
    public String deleteBook(
            Model model,
            @PathVariable SubGroup subGroup,
            @PathVariable Book book
            ){
        subGroupService.removeBook(subGroup, book);

        return "redirect:/subGroup/"+subGroup.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','LESSON_OVERSEER')")
    @GetMapping("/add/{subGroup}/{book}")
    public String addBook(
            Model model,
            @PathVariable SubGroup subGroup,
            @PathVariable Book book
    ){
        subGroupService.addBook(subGroup, book);

        return "redirect:/subGroup/"+subGroup.getId();
    }
}
