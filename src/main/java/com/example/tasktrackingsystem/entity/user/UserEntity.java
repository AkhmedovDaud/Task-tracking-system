package com.example.tasktrackingsystem.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATRONYMIC")
    private String patronymic;

    @Column(name = "TASKS")
    private String tasks;

    @Column(name = "PROJECTS")
    private String projects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && surname.equals(that.surname) && name.equals(that.name) &&
                Objects.equals(patronymic, that.patronymic) && Objects.equals(tasks, that.tasks) &&
                Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, tasks, projects);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", task='" + tasks + '\'' +
                ", project='" + projects + '\'' +
                '}';
    }
}
