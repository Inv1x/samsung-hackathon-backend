package com.samsung_hackathon.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
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
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ColumnTask> columnTasks = new HashSet<>();;

    @PrimaryKeyJoinColumn(name = "from_board")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Board board;
}
