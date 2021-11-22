package com.javarestassuredtemplate.requests.issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostIssuesRequest extends RequestRestBase {
    public PostIssuesRequest(){
        requestService = "/api/rest/issues/";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
            String summary,
            String description,
            String category_name,
            String project_name){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/issues/PostIssuesJson.json").
                replace("$summary", summary).
                replace("$description", description ).
                replace("$Category_name", category_name ).
                replace("$project_name", project_name);
    }

}
