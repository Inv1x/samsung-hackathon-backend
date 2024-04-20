package com.samsung_hackathon.backend.service.impl;

import com.samsung_hackathon.backend.dao.AuthorityRepository;
import com.samsung_hackathon.backend.entity.Authority;
import com.samsung_hackathon.backend.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    @Override
    public Authority addAuthority(Authority authority) {
        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());

        if (optionalAuthority.isPresent()) return optionalAuthority.get();
        else return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }
}
