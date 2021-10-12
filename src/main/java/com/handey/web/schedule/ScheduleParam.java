package com.handey.web.schedule;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleParam {
    private String content;

    private LocalDateTime startDt;

    private LocalDateTime endDt;
}
