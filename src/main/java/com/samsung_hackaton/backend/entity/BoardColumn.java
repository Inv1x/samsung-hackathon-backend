package com.samsung_hackaton.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "column")
public class BoardColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "heading")
    private String heading;

    @Column(name = "tasks")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ColumnTask> columnTasks;

    @PrimaryKeyJoinColumn(name = "from_board")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Board board;
}
