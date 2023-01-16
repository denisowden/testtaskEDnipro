package com.journal.demo.repository;

import com.journal.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findById(Integer id);
    Optional<Subject> findByNameSubject(String name);
}
