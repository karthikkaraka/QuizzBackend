package com.karthik.QuizzBackend.controller;

import com.karthik.QuizzBackend.Model.User;
import com.karthik.QuizzBackend.Service.JwtService;
import com.karthik.QuizzBackend.Service.UserSecurityService;
import com.karthik.QuizzBackend.repo.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.karthik.QuizzBackend.Model.Role.USER;

@RestController
public class UsersecurityController {
    @Autowired
    UserSecurityService userserivce;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtservice;
    @Autowired
    UserSecurityRepo userrepo;
    @PostMapping("register")
    public User register(@RequestBody User user)
    {
        System.out.println("entered for register.");
        user.setRole(USER);
        return userserivce.register(user);
    }
    @PostMapping("login")
    public String login(@RequestBody User user)
    {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        User realuser = userrepo.findByUserName(user.getUserName());
        if(auth.isAuthenticated())
        {
            return jwtservice.generateToken(realuser,user.getUserName());
        }
        else{
            return"FAILURE";
        }
    }

    @PostMapping("api/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    public User adminregister(@RequestBody User user)
    {
        System.out.println("entered for register.");
        user.setRole(USER);
        return userserivce.register(user);
    }

}
