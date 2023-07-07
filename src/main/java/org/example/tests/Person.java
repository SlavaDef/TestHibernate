package org.example.tests;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "person")
@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // анатація вказує що id автоінкримент
    @Id
    private long id;

    // @CollectionTable вкаже на те що лист зберігається в окремій таблиці
    @ElementCollection // базовий атрибут
    @CollectionTable(name = "person_address", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "address")
    private List<String> addressList;


}
