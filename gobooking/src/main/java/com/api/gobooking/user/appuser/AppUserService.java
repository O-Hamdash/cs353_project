package com.api.gobooking.user.appuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public List<AppUser> getAppUsers(){
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getAppUser(Integer id){
        return appUserRepository.findById(id);
    }

    public boolean addAppUser(AppUserRequest appUserRequest){
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(appUserRequest.getEmail());

        if (optionalAppUser.isPresent()){
            throw new IllegalStateException(String.format("addAppUser: AppUser with email (%s) already exists", appUserRequest.getEmail()));
        }

        AppUser appUser = new AppUser(appUserRequest);

        appUserRepository.save(appUser);

        return true;
    }

    public boolean deleteAppUser(Integer id){
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isEmpty()){
            throw new IllegalStateException(String.format("deleteAppUser: AppUser with id (%d) does not exist", id));
        }

        appUserRepository.deleteById(id);

        return true;
    }

    public boolean updateAppUser(Integer id, String name, String surname, String password, LocalDateTime birthDate){
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isEmpty()){
            throw new IllegalStateException(String.format("updateAppUser: AppUser with id (%d) does not exist", id));
        }

        AppUser appUser = optionalAppUser.get();

        if (name != null){
            appUser.setName(name);
        }
        if (surname != null){
            appUser.setSurname(surname);
        }
        if (password != null){
            appUser.setPassword(password);
        }
        if (birthDate != null){
            appUser.setBirthDate(birthDate);
        }

        appUserRepository.updateAppUser(appUser);

        return true;
    }

    public boolean setIsBannedFromBooking(Integer id, Boolean isBannedFromBooking){
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isEmpty()){
            throw new IllegalStateException(String.format("setIsBannedFromBooking: AppUser with id (%d) does not exist", id));
        }

        appUserRepository.setIsBannedFromBooking(id, isBannedFromBooking);

        return true;
    }

    public boolean setIsBannedFromPosting(Integer id, Boolean isBannedFromPosting){
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isEmpty()){
            throw new IllegalStateException(String.format("setIsBannedFromPosting: AppUser with id (%d) does not exist", id));
        }

        appUserRepository.setIsBannedFromPosting(id, isBannedFromPosting);

        return true;
    }
}
