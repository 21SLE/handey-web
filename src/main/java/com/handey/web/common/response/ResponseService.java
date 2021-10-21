package com.handey.web.common.response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    /**
     * success==true이고 return하는 데이터가 하나인 경우
     */
    public <T> SingleResponse<T> returnSingleResponse(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    /**
     * success==true이고 return하는 데이터가 여러개(List)인 경우
     */
    public <T> MultipleResponse<T> returnMultipleResponses(List<T> data) {
        MultipleResponse<T> response = new MultipleResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    /**
     * success==true만 return
     */
    public Response returnSuccessResponse() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    /**
     * fail했을 때 success==false를 return
     */
    public Response returnFailedResponse() {
        Response response = new Response();
        response.setSuccess(false);
        return response;
    }
}
