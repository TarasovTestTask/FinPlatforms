package com.example.finplatforms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentDto {

    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String dateOfBirth;

}
