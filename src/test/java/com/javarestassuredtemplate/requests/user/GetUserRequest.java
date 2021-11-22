package com.javarestassuredtemplate.requests.user;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetUserRequest extends RequestRestBase {
    public GetUserRequest(){
        requestService = "/api/rest/users/me";
        method = Method.GET;
    }

}
