package com.handey.web.controller.settings;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserInfoParam {
    private String resetTime;

    private String theme;
}
