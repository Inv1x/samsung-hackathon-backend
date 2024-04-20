package com.samsung_hackathon.backend.service;

import com.samsung_hackathon.backend.entity.Authority;

import java.util.List;

public interface AuthorityService {

    Authority addAuthority(Authority authority);

    List<Authority> getAll();
}
