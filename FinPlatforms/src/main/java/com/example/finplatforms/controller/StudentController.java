package com.example.finplatforms.controller;

import com.example.finplatforms.dto.request.AddStudentRequestDto;
import com.example.finplatforms.dto.response.StudentsResponseDto;
import com.example.finplatforms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public List<StudentsResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(path = "/add")
    public String add(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        try {
            studentService.add(addStudentRequestDto);
            return "Студент добавлен";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public String studentUpdate(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        try {
            studentService.add(addStudentRequestDto);
            return "Студент с id: " + addStudentRequestDto.getId() + " изменён";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deletedStudent(@PathVariable Long id) {
        try {
            studentService.deletedStudent(id);
            return "Студент c ID: " + id + " удален";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}


