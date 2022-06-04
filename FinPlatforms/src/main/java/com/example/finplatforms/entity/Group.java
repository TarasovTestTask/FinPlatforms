package com.example.finplatforms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "university_group")
@Setter
@Getter
@NoArgsConstructor
public class Group extends University {

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

}
