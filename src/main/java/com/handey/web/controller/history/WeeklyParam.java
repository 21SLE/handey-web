package com.handey.web.controller.history;

import com.handey.web.domain.history.WeeklyElm;
import lombok.Data;

import java.util.List;

@Data
public class WeeklyParam {

    private String title;

    private boolean clear;

    private List<WeeklyElm> weeklyElmList;

}
