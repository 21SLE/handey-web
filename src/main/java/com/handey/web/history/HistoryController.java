package com.handey.web.history;

import com.handey.web.afterhistory.AfterHistory;
import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.ResponseService;
import com.handey.web.todohistory.ToDoBoxHst;
import com.handey.web.todohistory.ToDoBoxHstService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HistoryController {
    private final ResponseService responseService;
    private final ToDoBoxHstService toDoBoxHstService;
    private final AfterHistoryService afterHistoryService;

    public HistoryController(ResponseService responseService, ToDoBoxHstService toDoBoxHstService, AfterHistoryService afterHistoryService) {
        this.responseService = responseService;
        this.toDoBoxHstService = toDoBoxHstService;
        this.afterHistoryService = afterHistoryService;
    }

    /**
     * 회원 투두 + 애프터 히스토리 전체 조회
     */
    @GetMapping("/user/{userId}/history")
    public ListResponse<History> getHstListByUserId(@PathVariable Long userId) {
        List<ToDoBoxHst> toDoBoxHstList = toDoBoxHstService.getToDoBoxHstListByUserId(userId);
        System.out.println(toDoBoxHstList);
        List<AfterHistory> afterHstList = afterHistoryService.getAfterListByUserId(userId);
        afterHstList.forEach(afterHistory -> System.out.println(afterHistory.getContent()));
        Map<LocalDate, History> hstMap = new HashMap<>();
        History newHistory = new History();
        newHistory.toDoBoxHstList = new ArrayList<>();
        newHistory.afterHstList = new ArrayList<>();
        toDoBoxHstList.forEach(toDoBoxHst -> {
            LocalDate saveDt = toDoBoxHst.getSaveDt();
            if(hstMap.containsKey(saveDt)){
                hstMap.get(saveDt).toDoBoxHstList.add(toDoBoxHst);
            }
            else {
                newHistory.setSaveDt(toDoBoxHst.getSaveDt());
                newHistory.toDoBoxHstList.add(toDoBoxHst);
                hstMap.put(saveDt, newHistory);
            }
        });
        afterHstList.forEach(afterHistory -> {
            hstMap.get(afterHistory.getHist_date()).afterHstList.add(afterHistory);
        });
        List<History> hstList = new ArrayList<History>(hstMap.values());
        return responseService.returnListResponse(hstList);
    }
}
