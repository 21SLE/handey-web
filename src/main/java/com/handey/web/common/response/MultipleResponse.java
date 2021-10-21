package com.handey.web.common.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultipleResponse<T> extends Response {
    private List<T> data;
}
