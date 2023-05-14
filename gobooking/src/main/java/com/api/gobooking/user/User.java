package com.api.gobooking.user;


import com.api.gobooking.user.admin.Admin;
import com.api.gobooking.user.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString

@Table(name = "`user`")
@Entity

@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Admin.class, name = "Admin"),
        @JsonSubTypes.Type(value = AppUser.class, name = "AppUser")}
)
@PrimaryKeyJoinColumn(name = "id")

public class User {
    @Id
    @Column(name = "id")
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp birthDate;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String name, String surname, String email, String password, Timestamp birthDate, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }
}