package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Pattern(regexp = "^[0-9]{3}[-][0-9]{3}[-][0-9]{3}+$")
    private String phone;

    @NotBlank
    @Column(unique = true)
    private String mail;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @NotBlank
    private String password;

    @ManyToMany
    @JoinColumn(name = "courseEditions")
    private List<CourseEdition> editions;
}
