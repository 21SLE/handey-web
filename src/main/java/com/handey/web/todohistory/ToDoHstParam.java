package com.handey.web.todohistory;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ToDoHstParam {
    LocalDate searchDt;
    String searchText;
}
