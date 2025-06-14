package com.karthik.QuizzBackend.Service;

import com.karthik.QuizzBackend.Model.User;
import com.karthik.QuizzBackend.repo.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private UserSecurityRepo userrepo;
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
       return userrepo.save(user);
    }
}
