package com.studying.springrestapplication.model.entity;

import com.studying.springrestapplication.model.enumeration.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"username", "role"})
@ToString(of = {"username", "role"})
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
