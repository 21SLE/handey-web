package com.handey.web.controller.history;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ToDoHstParam {
    LocalDate searchDt;
    String searchText;
}
