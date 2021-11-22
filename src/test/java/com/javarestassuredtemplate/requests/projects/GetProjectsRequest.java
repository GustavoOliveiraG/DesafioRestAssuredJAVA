package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetProjectsRequest extends RequestRestBase {
    public GetProjectsRequest(int ID){
        requestService = "/api/rest/projects/" + ID;
        method = Method.GET;
    }

}
