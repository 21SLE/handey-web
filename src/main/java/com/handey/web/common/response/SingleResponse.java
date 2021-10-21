package com.handey.web.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends Response {
    private T data;
}
