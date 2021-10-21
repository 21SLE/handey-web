package com.handey.web.memo;

import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MemoController {
    private final MemoService memoService;
    private final ResponseService responseService;

    public MemoController(MemoService memoService, ResponseService responseService) {
        this.memoService = memoService;
        this.responseService = responseService;
    }

    /**
     * 메모 조회
     */
    @GetMapping("/user/{userId}/memo")
    public SingleResponse<Optional<Memo>> getMemoByUserId(@PathVariable Long userId) {
        return responseService.returnSingleResponse(memoService.getMemoByUserId(userId));
    }

    /**
     * 메모 수정
     */
    @PutMapping("/user/{userId}/memo")
    public Response updateMemoContent(@PathVariable Long userId, @RequestBody MemoParam param) {
        if(memoService.updateMemoContent(userId, param))
            return responseService.returnSuccessResponse();
        else
            return responseService.returnFailResponse();
    }
}
