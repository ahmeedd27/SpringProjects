package com.Ahmed.AhmedSpring.repos;

import com.Ahmed.AhmedSpring.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTypeRepo extends JpaRepository<UsersType, Long> {
}
