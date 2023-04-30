package com.kingston.sqa.jobseeker.auth.repositories;

import com.kingston.sqa.jobseeker.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
}