package com.api.gobooking.user;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gobooking/user")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable(name = "userId") int userId){
        return userService.getUser(userId);
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session) {
        // Perform authentication logic here, such as validating username and password
        if (userService.getUserByEmail(email) != null && userService.getUserByEmail(email).getPassword().equals(password)) {
            // Authentication successful, set user information in session
            session.setAttribute("userId", userService.getUserByEmail(email).getId());
            return "redirect:/home";
        } else {
            // Authentication failed, redirect back to login page with an error message
            return "redirect:/login?error";
        }
    }
}
