package com.journal.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "students_subjects")
public class StudentSubject implements Cloneable{

    @Id
    @Column(name = "student_subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "mark")
    private Integer mark;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
