package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelProjectsRequest extends RequestRestBase {
    public DelProjectsRequest(int ID){
        requestService = "/api/rest/projects/" + ID ;
        method = Method.DELETE;
    }

}

