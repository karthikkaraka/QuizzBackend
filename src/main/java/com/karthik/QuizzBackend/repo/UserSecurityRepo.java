package com.karthik.QuizzBackend.repo;

import com.karthik.QuizzBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepo extends JpaRepository<User,Long> {

    User findByUserName(String username);
}
