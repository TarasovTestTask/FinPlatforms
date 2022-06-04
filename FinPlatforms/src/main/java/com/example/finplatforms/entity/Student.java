package com.example.finplatforms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Setter
@Getter
@NoArgsConstructor
public class Student extends University {

    @Column(name = "surname")
    private String surname;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
