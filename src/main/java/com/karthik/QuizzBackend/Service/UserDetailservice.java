package com.karthik.QuizzBackend.Service;

import com.karthik.QuizzBackend.Model.User;
import com.karthik.QuizzBackend.Model.Userprinciple;
import com.karthik.QuizzBackend.repo.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
@Service
public class UserDetailservice implements UserDetailsService {

    @Autowired
    UserSecurityRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("user not foumd!!!");
        }
        else{
            return new Userprinciple(user);
        }
    }
}
