package com.api.gobooking.user;


import com.api.gobooking.user.admin.Admin;
import com.api.gobooking.user.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Table(name = "`USER`")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Admin.class, name = "Admin"),
        @JsonSubTypes.Type(value = AppUser.class, name = "AppUser")}
)
public abstract class User {
    @Id
    @Column(name = "user_id")
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDateTime birthDate;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(int id, String name, String surname, String email, LocalDateTime birthDate, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = null;
        this.birthDate = birthDate;
        this.role = role;
    }
}