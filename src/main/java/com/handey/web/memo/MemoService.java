package com.handey.web.memo;


import com.handey.web.common.exception.MemoNoDataFoundException;
import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.memo.MemoParam;
import com.handey.web.memo.Memo;
import com.handey.web.member.Member;
import com.handey.web.memo.MemoRepository;
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
     * 메모 생성
     */
    public Long createMemo(Member member) {
        Memo memo = new Memo();
        memo.setMember(member);
        memoRepository.save(memo);
        return memo.getId();
    }

    /**
     * 메모 조회
     */
    public Optional<Memo> getMemoByUserId(Long userId) {
        return memoRepository.findByUserId(userId);
    }

    /**
     * 메모 내용 수정
     */
    public boolean updateMemoContent(Long userId, MemoParam param) {
        Memo memo = memoRepository.findByUserId(userId).orElseThrow(MemoNoDataFoundException::new);
        memo.updateMemo(param.getContent());
        return true;
    }

}
