package com.api.gobooking.user.appuser;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "gobooking/appuser")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAppUsers(){
        return appUserService.getAppUsers();
    }

    @GetMapping(path = "{appUserId}")
    public Optional<AppUser> getAppUser(@PathVariable("appUserId") Integer appUserId){
        return appUserService.getAppUser(appUserId);
    }

    @PostMapping
    public void addNewAppUser(@RequestBody AppUserRequest appUserRequest){
        appUserService.addAppUser(appUserRequest);
    }

    @DeleteMapping(path = "{appUserId}")
    public void deleteAppUser(@PathVariable("appUserId") Integer appUserId){
        appUserService.deleteAppUser(appUserId);
    }

    @PutMapping(path = "{appUserId}")
    public void updateAppUser( @PathVariable("appUserId") Integer appUserId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) String password,
                               @RequestParam(required = false) LocalDateTime birthDate
                            )
    {
        appUserService.updateAppUser(appUserId, name, surname, password, birthDate);
    }

    @PutMapping(path = "set_is_banned_from_booking/{appUserId}")
    public void setIsBannedFromBooking(@PathVariable("appUserId") Integer appUserId, @RequestParam Boolean isBannedFromBooking){
        appUserService.setIsBannedFromBooking(appUserId, isBannedFromBooking);
    }

    @PutMapping(path = "set_is_banned_from_posting/{appUserId}")
    public void setIsBannedFromPosting(@PathVariable("appUserId") Integer appUserId, @RequestParam Boolean isBannedFromPosting){
        appUserService.setIsBannedFromPosting(appUserId, isBannedFromPosting);
    }
}
