package com.handey.web.userinfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handey.web.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Getter
@Setter
@Table(name = "user_info")
@DynamicInsert
@DynamicUpdate
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resetTime", length = 2)
    private String resetTime;

//    @Column(name = "theme", length = 1)
//    private String theme;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

    @Transactional
    public void update(UserInfoParam userInfo) {
        String newResetTime = userInfo.getResetTime();
//        String newTheme = userInfo.getTheme();

        if(newResetTime != null)
            this.resetTime = newResetTime;
//        if(newTheme != null)
//            this.theme = newTheme;
    }
}
