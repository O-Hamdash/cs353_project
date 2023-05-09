package com.api.gobooking.user;

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
}
