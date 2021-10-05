package com.handey.web.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "join_mem")
@Getter
@Setter
public class Member {
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Override
    public boolean equals(Object ob){
        if(this == ob)
            return true;
        if(ob==null || getClass() != ob.getClass())
            return false;
        Member member = (Member)ob;
        return  id.equals(member.id)&&
                username.equals(member.username)&&
                email.equals(member.email)&&
                password.equals(member.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, username, email, password);
    }

    @Transactional
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
