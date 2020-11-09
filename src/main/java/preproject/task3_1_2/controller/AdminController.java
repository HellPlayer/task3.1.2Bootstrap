package preproject.task3_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import preproject.task3_1_2.model.Role;
import preproject.task3_1_2.model.User;
import preproject.task3_1_2.repo.UserRepo;

import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin")
    public String adminMain(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("roles", Role.values());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        model.addAttribute("roles", Role.values());
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam Set<Role> roles
                             ) {
        User user = new User(username, password, email);
        user.setRoles(roles);

        userRepo.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{username}")
    public String deleteUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userRepo.findByUsername(username));
        return "delete";
    }

    @PostMapping("/admin/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userRepo.delete(userRepo.findByUsername(username));
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{username}")
    public String editUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userRepo.findByUsername(username));
        model.addAttribute("roles", Role.values());
        return "edit";
    }

    @PostMapping("/admin/edit/{username}")
    public String editUser(@PathVariable String username,
                           @RequestParam String newUsername,
                           @RequestParam String newPassword,
                           @RequestParam String newEmail,
                           @RequestParam Set<Role> roles
    ) {
        User user = userRepo.findByUsername(username);
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        user.getRoles().clear();
        user.setRoles(roles);
        userRepo.save(user);
        return "redirect:/admin";
    }
}
