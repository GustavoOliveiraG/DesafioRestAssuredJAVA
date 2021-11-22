package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostProjectsVersionRequest extends RequestRestBase {
    public PostProjectsVersionRequest(int ID) {
        requestService = "/api/rest/projects/" + ID + "/versions/";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
                            String projectVersion,
                            String projectDescription,
                            String projectRelease,
                            String projectObsolete,
                            String projectDate){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PostProjectsVersionJson.json").
                                replace("$projectVersion", projectVersion).
                                replace("$projectDescription",(projectDescription)).
                                replace("$projectRelease", projectRelease ).
                                replace("$projectObsolete", projectObsolete ).
                                replace("$projectDate", String.valueOf(projectDate));
    }


}
