package com.javarestassuredtemplate.requests.issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetIssuesRequest extends RequestRestBase {
    public GetIssuesRequest(int idIssue){
        requestService = "/api/rest/issues/" + idIssue;
        method = Method.GET;
    }

}
