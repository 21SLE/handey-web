package com.handey.web.todo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ToDoBoxIndexParam {
    // spring의 잭슨을 통해 json으로 변환해줌
    private Long id;

    private Long index;
}
