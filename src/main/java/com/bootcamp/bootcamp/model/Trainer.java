package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "{com.bootcamp.bootcamp.model.Trainer.firstName.NotEmpty}")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;

    @NotEmpty(message = "{com.bootcamp.bootcamp.model.Trainer.lastName.NotEmpty}")
    private String lastName;

    @Positive(message = "{com.bootcamp.bootcamp.model.Trainer.salary.Positive}")
    private int salary;

    @NotEmpty(message = "{com.bootcamp.bootcamp.model.Trainer.description.NotEmpty}")
    private String description;

    @OneToMany
    private List<CourseEdition> editions;
}
