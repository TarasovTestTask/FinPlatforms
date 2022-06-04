package com.example.finplatforms;

import com.example.finplatforms.entity.Group;
import com.example.finplatforms.entity.Student;
import com.example.finplatforms.repository.GroupRepository;
import com.example.finplatforms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartUtils implements CommandLineRunner {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        addRGroup();
        addStudents();
    }

    Group group = new Group();
    private void addRGroup() {
        group = new Group();
        group.setName("Mathematics");
        groupRepository.save(group);
    }

    private void addStudents() {
        Student student = new Student();
        student.setName("Anton");
        student.setSurname("Osipov");
        student.setMiddleName("Ivanovich");
        student.setDateOfBirth("11.09.1996");
        student.setGroup(group);
        studentRepository.save(student);
    }
}

