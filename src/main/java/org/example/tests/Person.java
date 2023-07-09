package org.example.tests;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Table(name = "person")
@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // анатація вказує що id автоінкримент
    @Id
    private long id;
// use separate table
    // @CollectionTable вкаже на те що лист зберігається в окремій таблиці
    /* @ElementCollection // базовий атрибут
     @CollectionTable(name = "person_address", joinColumns = @JoinColumn(name = "person_id"))
     @Column(name = "address") */

    // use the same table
    @Column(name = "addresses")
    @Convert(converter = AddressConvector.class) // щоб все ок мапилось юзаємо конвектор поле обробляється спец чином
    private List<String> addressList;

    @OneToOne(cascade = CascadeType.ALL) // анотація звязка один до одного
    @JoinColumn(name = "id", referencedColumnName = "person_id") // який саме звяязок налаштовуємо
    private PersonInfo personInfo; //звязок між сутністю Person і PersonInfo

    @OneToMany(mappedBy = "person") // налаштували двох сторонній звязок один до багатьох
    private List<WorkPlace> workPlaces;


    @JoinTable(name ="person_project",    // назва таблиці
    joinColumns = @JoinColumn(name = "person_id"), // з одного боку таке
    inverseJoinColumns = @JoinColumn(name = "project_id")) // з іншого таке
    @ManyToMany(fetch = FetchType.LAZY)// звязок можна налаштовувати в любому із звязующихся класів
    private Set<Project> projects;


}

// ON DELETE CASCADE - повязані запити мають видалитися
// FOREIGN KEY (person_id) REFERENCES person(id) - значення поля із таблиці person_info має
// відповідати значенню поля id із таблиці person
// fetch = FetchType.EAGER уточнюємо про примусове витягування данних з таблиці
// fetch = FetchType.LAZY - в 90 випадках вірна поведінка така