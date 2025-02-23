package com.Ahmed.AhmedSpring.repos;

import com.Ahmed.AhmedSpring.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
}
