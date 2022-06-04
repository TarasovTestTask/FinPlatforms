package com.example.finplatforms.controller;

import com.example.finplatforms.dto.request.AddGroupRequestDto;
import com.example.finplatforms.dto.response.GroupsResponseDto;
import com.example.finplatforms.dto.response.SingleGroupResponseDto;
import com.example.finplatforms.service.GroupService;
import com.example.finplatforms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping(path = "/add")
    public String add(@RequestBody AddGroupRequestDto groupDto) {
        try {
            groupService.add(groupDto);
            return "группа добавлена";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public String groupUpdate(@RequestBody AddGroupRequestDto requestDto) {
        try {
            groupService.add(requestDto);
            return "Группа c id: " + requestDto.getId() + " изменена";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/get")
    public List<GroupsResponseDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping(path = "/get/{id}")
    public SingleGroupResponseDto getGroup(@PathVariable Long id) {
        try {
            return groupService.getGroup(id);
        } catch (IllegalArgumentException e) {
            return new SingleGroupResponseDto()
                    .setErrorText(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            groupService.groupDeleted(id);
            return "Группа " + id + " удалена!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}



