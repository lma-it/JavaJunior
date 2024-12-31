package homework.seminar4.course_hibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @javax.persistence.Column(name = "duration")
    private String duration;

    public Course(){}

    public Course(String title, String duration){
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %s, Название: %s, Продолжительность: %s.", this.id, this.title, this.duration);
    }
}
