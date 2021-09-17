package com.handey.web.domain.join;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.Objects;

@Entity
@Table(name = "join")
@DynamicInsert
@DynamicUpdate

public class member {
    @Id
    @Column(name = "username") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object ob){
        if(this == ob)
            return true;
        if(ob==null || getClass() != ob.getClass())
            return false;
        member member = (member)ob;
        return username.equals(member.username)&&
                email.equals(member.email)&&
                password.equals(member.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(username, email, password);
    }
}
