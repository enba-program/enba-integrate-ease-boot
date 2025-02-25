package com.enba.integrate.forest.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.Request;
import com.enba.integrate.forest.interceptors.ApiClientInterceptor;


@BaseRequest(baseURL = "localhost:8080")
public interface TestInterceptorClient {

    @Request(
            url = "/receive-interceptor",
            type = "post",
            dataType = "text",
            interceptor = ApiClientInterceptor.class
    )
    String testInterceptor(@DataParam("username") String username);
}
