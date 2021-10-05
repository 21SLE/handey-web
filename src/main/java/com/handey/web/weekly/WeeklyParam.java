package com.handey.web.weekly;

import lombok.Data;

import java.util.List;

@Data
public class WeeklyParam {

    private String title;

    private boolean clear;

    private List<WeeklyElm> weeklyElmList;

}
