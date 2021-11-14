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
     * success==false이고 return하는 데이터가 하나인 경우
     */
    public <T> SingleResponse<T> returnSingleFailResponse() {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(false);
        return response;
    }

    /**
     * success==true이고 return하는 데이터가 여러개(List)인 경우
     */
    public <T> ListResponse<T> returnListResponse(List<T> data) {
        ListResponse<T> response = new ListResponse<>();
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
    public Response returnFailResponse() {
        Response response = new Response();
        response.setSuccess(false);
        return response;
    }
}
