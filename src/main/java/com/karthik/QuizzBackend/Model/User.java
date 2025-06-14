package com.karthik.QuizzBackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String userName;
   private String password;
   @Enumerated(EnumType.STRING)
   private Role role;
}
