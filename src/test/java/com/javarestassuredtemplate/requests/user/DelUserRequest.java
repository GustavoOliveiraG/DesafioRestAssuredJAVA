package com.javarestassuredtemplate.requests.user;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelUserRequest extends RequestRestBase {
    public DelUserRequest(int ID){
        requestService = "/api/rest/users/" + ID ;
        method = Method.DELETE;
    }

}

