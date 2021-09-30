package com.handey.web.controller.home;

import com.handey.web.domain.home.Memo;
import com.handey.web.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    /**
     * 메모 조회
     */
    @GetMapping("/user/{userId}/memo")
    public Optional<Memo> getMemoByUserId(@PathVariable Long userId) {
        return memoService.getMemoByUserId(userId);
    }

    /**
     * 메모 수정
     */
    @PutMapping("/user/{userId}/memo")
    public boolean updateMemoContent(@PathVariable Long userId, @RequestBody MemoParam param) {
        return memoService.updateMemoContent(userId, param);
    }
}
