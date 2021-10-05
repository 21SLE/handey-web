package com.handey.web.member;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MemberParam {
    private String username;

    private String password;
}
