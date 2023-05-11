package com.api.gobooking.user.admin;

import com.api.gobooking.user.Role;
import com.api.gobooking.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Admin extends User {

    @Enumerated(value = EnumType.STRING)
    private AdminRole adminRole;

    public Admin(String name, String surname, String email, String password, LocalDateTime birthDate, AdminRole adminRole){
        super(name, surname, email, password, birthDate, Role.ADMIN);

        this.adminRole = adminRole;
    }

    public Admin(AdminRequest adminRequest) {
        super(adminRequest.getName(), adminRequest.getSurname(), adminRequest.getEmail(), adminRequest.getPassword(), adminRequest.getBirthDate(), Role.ADMIN);

        this.adminRole = adminRequest.getAdminRole();
    }
}
