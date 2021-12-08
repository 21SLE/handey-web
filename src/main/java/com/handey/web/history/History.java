package com.handey.web.history;

import com.handey.web.afterhistory.AfterHistory;
import com.handey.web.todohistory.ToDoBoxHst;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class History {
    LocalDate saveDt;
    List<ToDoBoxHst> toDoBoxHstList;
    List<AfterHistory> afterHstList;

}
