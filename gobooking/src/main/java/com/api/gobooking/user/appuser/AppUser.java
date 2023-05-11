package com.api.gobooking.user.appuser;

import com.api.gobooking.user.Role;
import com.api.gobooking.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
//@Entity

//@Table
//@PrimaryKeyJoinColumn(name = "id")
public class AppUser extends User {
    private Double balance;
    private String city;
    private Integer taxNumber;
    private LocalDateTime registrationDate;
    private Boolean isBlocked;
    private Boolean isBannedFromBooking;
    private Boolean isBannedFromPosting;

    public AppUser(String name, String surname, String email, String password, LocalDateTime birthDate, Double balance, String city){
        super(name, surname, email, password, birthDate, Role.APP_USER);

        this.balance = balance;
        this.city = city;
        this.taxNumber = null;
        this.registrationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.isBannedFromBooking = false;
        this.isBannedFromPosting = false;
    }

    public AppUser(AppUser appUser){
        super(appUser.getName(), appUser.getSurname(), appUser.getEmail(), appUser.getPassword(), appUser.getBirthDate(), Role.APP_USER);

        this.balance = appUser.getBalance();
        this.city = appUser.getCity();
        this.taxNumber = null;
        this.registrationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.isBannedFromBooking = false;
        this.isBannedFromPosting = false;
    }

    public AppUser(AppUserRequest appUserRequest){
        super(appUserRequest.getName(), appUserRequest.getSurname(), appUserRequest.getEmail(), appUserRequest.getPassword(), appUserRequest.getBirthDate(), Role.APP_USER);

        this.balance = appUserRequest.getBalance();
        this.city = appUserRequest.getCity();
        this.taxNumber = null;
        this.registrationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.isBannedFromBooking = false;
        this.isBannedFromPosting = false;
    }
}