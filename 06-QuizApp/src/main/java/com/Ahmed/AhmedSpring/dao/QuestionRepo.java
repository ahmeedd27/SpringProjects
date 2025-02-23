package com.Ahmed.AhmedSpring.dao;

import com.Ahmed.AhmedSpring.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

      List<Question> findQuestionByCategory(String cat);

    @Query(value = "SELECT * FROM question WHERE category = :category " +
            "ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category,
                                                 @Param("numQ") int numQ);

}
