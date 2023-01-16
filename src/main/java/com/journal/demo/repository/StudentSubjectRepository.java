package com.journal.demo.repository;

import com.journal.demo.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {
    @Query(value = "SELECT  * " +
                    "FROM students_subjects " +
                    "JOIN students USING (student_id)" +
                    "JOIN subjects USING (subject_id)",
            nativeQuery = true)
    List<StudentSubject> getAllJournal();

    @Query(value = "SELECT " +
            "name_student, " +
            "ROUND(AVG(mark), 2) AS mark " +
            "FROM students_subjects " +
            "JOIN students USING (student_id) " +
            "GROUP BY name_student " +
            "HAVING AVG(mark) < ?1 ",
            nativeQuery = true)
    List<Object[]> getStudentsWithAverageMarkBelow(Integer mark);

    @Query(value = "SELECT * FROM students_subjects " +
            "JOIN subjects USING (subject_id) " +
            "WHERE name_subject = ?1 AND mark > ?2", nativeQuery = true)
    List<StudentSubject> getStudentWithMarkOver(String subject, Integer mark);

    @Query(value = "SELECT * FROM students_subjects " +
            "JOIN students USING (student_id) " +
            "WHERE name_student = ?1", nativeQuery = true)
    List<StudentSubject> getMarksByStudent(String student);
}
