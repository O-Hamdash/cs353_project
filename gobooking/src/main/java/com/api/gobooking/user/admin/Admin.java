package com.api.gobooking.user.admin;

import com.api.gobooking.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor

//@Table
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    @Enumerated(value = EnumType.STRING)
    private AdminRole adminRole;

}
