package com.samsung_hackathon.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {
    private long id;
    private String name;
    private String username;
    private String email;
}
