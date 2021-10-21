package com.handey.web.common.reset;

import com.handey.web.after.AfterService;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//clear가 1되면 바로 afterList로 이동하도록
@Component
public class ToAfterList {
    final private AfterService afterService;
    final private WeeklyService weeklyService;

    public ToAfterList(AfterService afterService, WeeklyService weeklyService) {
        this.afterService = afterService;
        this.weeklyService = weeklyService;
    }

//    @Transactional
//    public void clearMove() {
//        moveToAfter();
//    }

    @Transactional
    public void moveToAfter(){  //clear된 건 바로 after로 이동
        List<WeeklyBox> weeklyBoxList = weeklyService.getWeeklyBoxListCleared();

        //collection.forEach(변수 -> 반복처리(변수))
        weeklyBoxList.forEach(weeklyBox ->
                    afterService.createAfterBox(weeklyBox.getMember(), weeklyBox)
        );
    }
}
