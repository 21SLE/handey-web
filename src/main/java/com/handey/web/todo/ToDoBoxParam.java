package com.handey.web.todo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ToDoBoxParam {
    // spring의 잭슨을 통해 json으로 변환해줌
    private String title;

    private boolean fixed;

    //(todoboxId, index)
    private Map<String, String> indexList;
}
