package org.example.tests;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "person_info")
@Entity
@Data
public class PersonInfo {
    @Id
    @Column(name = " person_id")
    private long personId;

    @Column(name = " person_name")
    private String name;

    /* @OneToOne(cascade = CascadeType.ALL) // анотація звязка один до одного
    @JoinColumn(name = "id", referencedColumnName = "person_id") // який саме звяязок налаштовуємо
    private Person person; */





}
