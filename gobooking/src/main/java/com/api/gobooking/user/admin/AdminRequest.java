package com.api.gobooking.user.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDateTime birthDate;
    private AdminRole adminRole;
}
