package com.samsung_hackathon.backend.controller;

import com.samsung_hackathon.backend.entity.Authority;
import com.samsung_hackathon.backend.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authority")
public class AuthorityController {
    private final AuthorityService authorityService;

    @PostMapping
    public Authority addAuthority(@RequestBody Authority authority) {
        return authorityService.addAuthority(authority);
    }

    @GetMapping
    public List<Authority> getAll() {
        return authorityService.getAll();
    }
}
