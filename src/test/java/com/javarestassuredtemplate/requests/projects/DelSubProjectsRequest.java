package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelSubProjectsRequest extends RequestRestBase {
    public DelSubProjectsRequest(int IDProject, int IDSubProject){
        requestService = "/api/rest/projects/" + IDProject + "/subprojects/" + IDSubProject;
        method = Method.DELETE;
    }

}

