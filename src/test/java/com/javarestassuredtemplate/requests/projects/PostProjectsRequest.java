package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostProjectsRequest extends RequestRestBase {
    public PostProjectsRequest(){
        requestService = "/api/rest/projects/";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
                            String projectName,
                            int statusID,
                            String statusName,
                            String descriptionText,
                            int enableID,
                            int viewID,
                            String viewName){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PostProjectsJson.json").
                                replace("$projectName", projectName).
                                replace("$statusID", String.valueOf(statusID)).
                                replace("$statusName", statusName ).
                                replace("$descriptionText", descriptionText ).
                                replace("$enableID", String.valueOf(enableID)).
                                replace("$viewID", String.valueOf(viewID)).
                                replace("$viewName", viewName);
    }


}
