package com.example.finplatforms.service;

import com.example.finplatforms.dto.request.AddStudentRequestDto;
import com.example.finplatforms.dto.response.StudentsResponseDto;

import java.util.List;

public interface StudentService {
    void add(AddStudentRequestDto addStudentRequestDto);

    void deletedStudent(Long id);

    List<StudentsResponseDto> getAllStudents();

}
