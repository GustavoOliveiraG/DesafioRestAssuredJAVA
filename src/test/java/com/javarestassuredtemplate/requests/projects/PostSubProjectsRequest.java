package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostSubProjectsRequest extends RequestRestBase {
    public PostSubProjectsRequest(int ID){
        requestService = "/api/rest/projects/" + ID + "/subprojects";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
                            String subProjectName,
                            String inherit){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PostSubProjectsJson.json").
                                replace("$subProjectName", subProjectName).
                                replace("$inherit", inherit );
    }


}
