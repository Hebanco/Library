package eLibrary.controller;

import eLibrary.domain.Role;
import eLibrary.domain.User;
import eLibrary.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER_OVERSEER')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER_OVERSEER')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER_OVERSEER')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ){
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    @GetMapping("/teachers")
    public String teachersList(Model model){
        model.addAttribute("teachers",userService.findTeacher());
        return "teachers";
    }

    @GetMapping("/myProfile")
    public String myProfile(
            Model model,
            @AuthenticationPrincipal User user
    ){
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER_OVERSEER')")
    @GetMapping("/profile/{user}")
    public String userProfile(
            Model model,
            @PathVariable User user
    ){
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PostMapping("/profileSave")
    public String saveProfile(
        Model model,
        @RequestParam("userId") User user,
        @RequestParam(required = false, defaultValue = "", name = "username") String username,
        @RequestParam String password,
        @RequestParam String email
    ){
        userService.updateProfile(user, username, password, email);
        model.addAttribute("user", user);
        model.addAttribute("message", "Save successfully");
        return "userProfile";
    }
}
