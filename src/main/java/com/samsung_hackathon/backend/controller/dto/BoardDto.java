package com.samsung_hackathon.backend.controller.dto;

import com.samsung_hackathon.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private long id;
    private String title;
    private Set<AttachedColumnDto> columns;
    private UserShortDto owner;
    private Set<UserShortDto> collaborators;
}
