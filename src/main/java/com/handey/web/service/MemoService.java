package com.handey.web.service;


import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.controller.home.MemoParam;
import com.handey.web.domain.home.Memo;
import com.handey.web.repository.home.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MemoService {
    public final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    /**
     * 메모 생성 -> TODO 회원가입시 메모 자동 생성으로 수정
     */
    public Long createMemo() {
        Memo memo = new Memo();
        memoRepository.save(memo);
        return memo.getId();
    }

    /**
     * 메모 조회
     */
    public Optional<Memo> getMemo(Long memoId) {
        return memoRepository.findById(memoId);
    }

    /**
     * 메모 내용 수정
     */
    public boolean updateMemoContent(Long memoId, MemoParam param) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(ToDoNoDataFoundException::new);
        memo.updateMemo(param.getContent());
        return true;
    }

}
