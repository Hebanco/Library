package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.domain.LessonSubGroup;
import eLibrary.repos.BookRepo;
import eLibrary.repos.SubGroupRepo;
import eLibrary.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
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

//    @GetMapping("/new")
//    public String newGroup(Model model){
//        return "subGroupCreate";
//    }
//
//    @PostMapping("/new")
//    public String newGroup(
//            Model model,
//            @RequestParam String name
//    ){
//        LessonSubGroup subGroup = new LessonSubGroup();
//        subGroup.setName(name);
//
//        subGroupRepo.save(subGroup);
//
//        return "redirect:/subGroup/new";
//    }


    @GetMapping("{subGroup}")
    public String subGroupEditForm(
            @PathVariable LessonSubGroup subGroup,
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter
    ){
        model.addAttribute("subGroup", subGroup);
        model.addAttribute("books", subGroup.getGroupsBook());

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
            model.addAttribute("nameError","Name is Empty");
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
        subGroup.getGroupsBook().remove(book);
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
        subGroup.getGroupsBook().add(book);
        subGroupRepo.save(subGroup);

        return "redirect:/subGroup/"+subGroup.getId();
    }

    @PostMapping("subGroupFilter")
    public String subGroupFilter(
            Model model,
            @RequestParam("subGroupId") LessonSubGroup subGroup,
            @RequestParam String filter
    ){
        String s = "redirect:/subGroup/"+subGroup.getId()+"?filter="+filter;
        return s;
    }
}
