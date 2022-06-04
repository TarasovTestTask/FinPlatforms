package com.example.finplatforms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String dateOfBirth;
    private Long groupId;
}
