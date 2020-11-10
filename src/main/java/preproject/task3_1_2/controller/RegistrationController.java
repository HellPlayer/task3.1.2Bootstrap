package preproject.task3_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import preproject.task3_1_2.repo.UserRepo;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
