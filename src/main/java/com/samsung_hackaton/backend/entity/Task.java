package com.samsung_hackaton.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @PrimaryKeyJoinColumn(name = "assigned_to")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User assignedTo;

    @Column(name = "status")
    private boolean isDone = false;

    @Column(name = "column_id")
    private long columnId;
}
