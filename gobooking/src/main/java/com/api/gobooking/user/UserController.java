package com.api.gobooking.user;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/id/{userId}")
    public User getUserById(@PathVariable(name = "userId") int userId){
        return userService.getUser(userId);
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session){

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

    @PostMapping("/signup")
    public String signup(@RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         @RequestParam("birthdate") String birthdate,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         HttpSession session){

        User u = userService.getUserByEmail(email);
        if (u != null) {
            // User with the same email already exists, redirect back to signup page with an error message
            return "redirect:/signup?error=emailExists";
        }

        // Create a new user
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setBirthDate(Timestamp.valueOf(birthdate));
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(Role.APP_USER);

        // Save the new user to the database
        userService.saveUser(newUser);

        // Set user information in session
        session.setAttribute("userId", newUser.getId());

        // Redirect to the home page or any other desired destination
        return "redirect:/home";
    }


}
