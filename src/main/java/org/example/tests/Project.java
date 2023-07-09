package org.example.tests;


import jakarta.persistence.*;
import lombok.Data;


@Table(name = "project")
@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "project_name")
    private String name;
}
