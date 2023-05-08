package com.api.gobooking.user.appuser;

import com.api.gobooking.user.Role;
import com.api.gobooking.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity

//@Table
@PrimaryKeyJoinColumn(name = "id")
public class AppUser extends User {
    private Double balance;
    private String city;
    private Integer taxNumber;
    private LocalDateTime registrationDate;
    private Boolean isBlocked;
    private Boolean isBannedFromBooking;
    private Boolean isBannedFromPosting;

    public AppUser(Integer id, String name, String surname, String email, LocalDateTime birthDate, Double balance, String city){
        super(id, name, surname, email, birthDate, Role.APP_USER);

        this.balance = balance;
        this.city = city;
        this.taxNumber = null;
        this.registrationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.isBannedFromBooking = false;
        this.isBannedFromPosting = false;
    }

    public AppUser(AppUser appUser){
        super(appUser.getId(), appUser.getName(), appUser.getSurname(), appUser.getEmail(), appUser.getBirthDate(), Role.APP_USER);

        this.balance = appUser.getBalance();
        this.city = appUser.getCity();
        this.taxNumber = null;
        this.registrationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.isBannedFromBooking = false;
        this.isBannedFromPosting = false;
    }
}