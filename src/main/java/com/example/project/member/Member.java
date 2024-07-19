package com.example.project.member;


import com.example.project.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) //중복 허용 하지 않겠다.
    private String username;
    private String password;
    private String displayName; //닉네임

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.REMOVE)
    private List<Sales> salesList ;
}
