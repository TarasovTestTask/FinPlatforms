package com.example.finplatforms.service;

import com.example.finplatforms.dto.request.AddStudentRequestDto;
import com.example.finplatforms.dto.response.StudentsResponseDto;
import com.example.finplatforms.entity.Group;
import com.example.finplatforms.entity.Student;
import com.example.finplatforms.repository.GroupRepository;
import com.example.finplatforms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Override
    public void add(AddStudentRequestDto addStudentRequestDto) {
        if (addStudentRequestDto.getSurname() == null) {
            throw new IllegalArgumentException("НЕ ПРАВИЛЬНО ПЕРЕДАНО ИМЯ");
        }
        if (addStudentRequestDto.getGroupId() != null) {
            //1. Получить группу в которую будем добавлять студента
            Optional<Group> optionalGroup = groupRepository.findById(addStudentRequestDto.getGroupId());
            Group group = optionalGroup.orElseThrow(() -> {
                throw new IllegalArgumentException("Группа ID: " + addStudentRequestDto.getGroupId() + " не найдена");
            });
            //2. Наполняем студента и добавляем его в полученную группу
            Student student = new Student();
            student.setName(addStudentRequestDto.getName());
            student.setSurname(addStudentRequestDto.getSurname());
            student.setMiddleName(addStudentRequestDto.getMiddleName());
            student.setDateOfBirth(addStudentRequestDto.getDateOfBirth());
            student.setGroup(group);
            //3. Сохраняем студента
            studentRepository.save(student);
        }
        // Логика для изменения студента
        if (addStudentRequestDto.getId() != null) {
            Optional<Student> byId = studentRepository.findById(addStudentRequestDto.getId());
            byId.orElseThrow(() -> {
                throw new IllegalArgumentException("студент c ID: " + addStudentRequestDto.getId() + " не найден");
            });
            Student student = byId.get();
            student.setName(addStudentRequestDto.getName());
            student.setSurname(addStudentRequestDto.getSurname());
            student.setMiddleName(addStudentRequestDto.getMiddleName());
            student.setDateOfBirth(addStudentRequestDto.getDateOfBirth());
            studentRepository.save(student);
        }
    }

    @Override
    public void deletedStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.orElseThrow(() -> {
            throw new IllegalArgumentException("студент ID: " + id + " не найден");
        });
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentsResponseDto> getAllStudents() {
        List<Student> all = studentRepository.findAll();
        return all.stream().map(e -> new StudentsResponseDto()
                        .setId(e.getId())
                        .setName(e.getName())
                        .setSurname(e.getSurname())
                        .setMiddleName(e.getMiddleName())
                        .setDateOfBirth(e.getDateOfBirth())
                        .setGroupId(e.getGroup().getId()))
                .collect(Collectors.toList());
    }
}

