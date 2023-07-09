package org.example.tests;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table(name = "workplace")
@Entity
@Data
public class WorkPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude // треба щоб стрінги норм поверталися
    @ManyToOne
    private Person person; // до якої людини відноситься

    private String place;
}
