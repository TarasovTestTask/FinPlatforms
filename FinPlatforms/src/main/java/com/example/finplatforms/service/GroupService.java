package com.example.finplatforms.service;

import com.example.finplatforms.dto.request.AddGroupRequestDto;
import com.example.finplatforms.dto.response.GroupsResponseDto;
import com.example.finplatforms.dto.response.SingleGroupResponseDto;

import java.util.List;

public interface GroupService {
    void add(AddGroupRequestDto groupDto);

    List<GroupsResponseDto> getAllGroups();

    SingleGroupResponseDto getGroup(Long id);

    void groupDeleted(Long id);

}
