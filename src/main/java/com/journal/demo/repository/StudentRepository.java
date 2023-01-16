package com.journal.demo.repository;

import com.journal.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(Integer id);
    Optional<Student> findByNameStudent(String name);

    List<Student> findAll();
}
