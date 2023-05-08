package com.api.gobooking.user.admin;

import com.api.gobooking.user.Role;
import com.api.gobooking.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Admin extends User {

    @Enumerated(value = EnumType.STRING)
    private AdminRole adminRole;

    public Admin(int id, String name, String surname, String email, LocalDateTime birthDate, AdminRole adminRole){
        super(id, name, surname, email, birthDate, Role.ADMIN);

        this.adminRole = adminRole;
    }
}
