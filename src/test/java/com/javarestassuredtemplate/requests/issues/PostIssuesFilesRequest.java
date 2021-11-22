package com.javarestassuredtemplate.requests.issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostIssuesFilesRequest extends RequestRestBase {
    public PostIssuesFilesRequest(int idBug){
        requestService = "/api/rest/issues/" +idBug+ "/files";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
            String name,
            String content){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/issues/PostIssuesFilesJson.json").
                replace("$name", name).
                replace("$content", content );
    }

}
