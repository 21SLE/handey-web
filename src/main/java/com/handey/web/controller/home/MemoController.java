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
    @GetMapping("/memo/{memoId}")
    public Optional<Memo> getMemo(@PathVariable Long memoId) {
        return memoService.getMemo(memoId);
    }

    /**
     * 메모 생성
     */
    @PostMapping("/memo")
    public Long createMemo() {
        return memoService.createMemo();
    }

    /**
     * 메모 수정
     */
    @PutMapping("/memo/{memoId}")
    public boolean updateMemoContent(@PathVariable Long memoId, @RequestBody MemoParam param) {
        return memoService.updateMemoContent(memoId, param);
    }
}
