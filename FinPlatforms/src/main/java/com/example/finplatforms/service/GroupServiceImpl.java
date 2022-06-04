package com.example.finplatforms.service;

import com.example.finplatforms.dto.request.AddGroupRequestDto;
import com.example.finplatforms.dto.response.GroupsResponseDto;
import com.example.finplatforms.dto.response.SingleGroupResponseDto;
import com.example.finplatforms.dto.response.StudentDto;
import com.example.finplatforms.entity.Group;
import com.example.finplatforms.entity.Student;
import com.example.finplatforms.repository.GroupRepository;
import com.example.finplatforms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public void add(AddGroupRequestDto groupDto) {
        if (groupDto.getName() == null) {
            throw new IllegalArgumentException("Не верно передано имя группы");
        }
        if (groupDto.getId() == null) {
            Group group = new Group();
            group.setName(groupDto.getName());
            groupRepository.save(group);
        } else {
            Optional<Group> byId = groupRepository.findById(groupDto.getId());
            byId.orElseThrow(() -> {
                throw new IllegalArgumentException("Группа ID: " + groupDto.getId() + " не найдена");
            });
            Group group = byId.get();
            group.setName(groupDto.getName());
            groupRepository.save(group);
        }
    }

    @Override
    public List<GroupsResponseDto> getAllGroups() {
        //1. Получить список всех Group
        List<Group> groups = groupRepository.findAll();

        //2. Меппинг энтити в приемлимый формат ответа т.е DTO
        return groups.stream().map(e -> new GroupsResponseDto()
                        .setId(e.getId())
                        .setGroupName(e.getName())
                        .setQuantity(e.getStudents().size()))
                .collect(Collectors.toList());
    }

    @Override
    public SingleGroupResponseDto getGroup(Long id) {
        //1. Получить Group Id
        Optional<Group> byId = groupRepository.findById(id);
        Group gr = byId.orElseThrow(() -> {
            throw new IllegalArgumentException("Группа c ID: " + id + " не найдена");
        });
        Group group = byId.get();
        SingleGroupResponseDto singleGroupResponseDto = new SingleGroupResponseDto();
        singleGroupResponseDto.setGroupName(group.getName());
        singleGroupResponseDto.setId(group.getId());

        Set<Student> students = group.getStudents();
        List<StudentDto> collect = students.stream().map(e -> new StudentDto()
                        .setId(e.getId())
                        .setName(e.getName())
                        .setSurname(e.getSurname())
                        .setMiddleName(e.getMiddleName())
                        .setDateOfBirth(e.getDateOfBirth()))
                .collect(Collectors.toList());

        singleGroupResponseDto.setStudents(collect);
        return singleGroupResponseDto;
    }

    @Override
    public void groupDeleted(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        optionalGroup.orElseThrow(() -> {
            throw new IllegalArgumentException("Группа ID: " + id + " не найдена");
        });
        groupRepository.deleteById(id);
    }
}
