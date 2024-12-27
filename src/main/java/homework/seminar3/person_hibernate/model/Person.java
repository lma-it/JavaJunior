package homework.seminar3.person_hibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Person(){}

    public Person(String name,
                  String lastName,
                  String surname,
                  int age,
                  LocalDate birthDate,
                  String phone,
                  String email){
        this.name = name;
        this.lastName = lastName;
        this.surname = surname;
        this.age = age;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, Name: %s, Last Name: %s, Surname: %s, Age: %s, Date of birth: %s, Phone: %s, Email: %s",
                this.id,
                this.name,
                this.lastName,
                this.surname,
                this.age,
                this.birthDate,
                this.phone,
                this.email);
    }
}
