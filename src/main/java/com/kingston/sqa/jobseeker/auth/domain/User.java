package com.kingston.sqa.jobseeker.auth.domain;

import com.kingston.sqa.jobseeker.auth.dto.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private  String username;
    private  String password;
    private LocalDate birthDay;
    private String mobileNumber;
    private String gender;
    private String address;
    private String email;
    private UserType role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
