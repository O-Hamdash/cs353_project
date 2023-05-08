package com.api.gobooking.user.appuser;

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
    private double balance;
    private String city;
    private Integer taxNumber;
    private LocalDateTime registrationDate;
    private boolean isBlocked;
    private boolean isBannedFromBooking;
    private boolean isBannedFromPosting;

    public AppUser(double balance, String city){
        this.balance = balance;
        this.city = city;
        this.taxNumber = null;
        this.registrationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.isBannedFromBooking = false;
        this.isBannedFromPosting = false;
    }
}
