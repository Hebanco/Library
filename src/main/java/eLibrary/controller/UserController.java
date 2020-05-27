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
    public String userList(
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter){
        model.addAttribute("users", userService.findAll(filter));
        model.addAttribute("filter",filter);
        return "userList";
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
        model.addAttribute("roles", Role.values());
        return "userProfile";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER_OVERSEER')")
    @GetMapping("/profile/{user}")
    public String userProfile(
            Model model,
            @PathVariable User user
    ){
        model.addAttribute("roles", Role.values());
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PostMapping("/profileSave")
    public String saveProfile(
            Model model,
            @RequestParam("userId") User user,
            @RequestParam(required = false, defaultValue = "", name = "username") String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam(required = false, defaultValue = "") String fio,
            @RequestParam(required = false, defaultValue = "false") boolean roleCheck,
            @RequestParam Map<String, String> form
    ){
        userService.updateProfile(user, username, password, email, fio);
        if (roleCheck) {
            userService.saveRoles(user, form);
        }
        model.addAttribute("user", user);
        model.addAttribute("success", "true");
        model.addAttribute("roles", Role.values());
        return "userProfile";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER_OVERSEER')")
    @GetMapping("/delete/{user}")
    public String deleteUser(
            Model model,
            @PathVariable User user
    ){
        userService.delete(user);
        return "redirect:/user";
    }
}
