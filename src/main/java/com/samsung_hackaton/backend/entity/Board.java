package com.samsung_hackaton.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "columns")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BoardColumn> columns;

    @PrimaryKeyJoinColumn(name = "owner")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    @Column(name = "collaborators")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> collaborators;
}
