package com.handey.web.history;

import com.handey.web.finishedweekly.FwBox;
import com.handey.web.todohistory.ToDoBoxHst;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class History {
    LocalDate saveDt;
    List<ToDoBoxHst> toDoBoxHstList;
    List<FwBox> fwBoxList;

}
