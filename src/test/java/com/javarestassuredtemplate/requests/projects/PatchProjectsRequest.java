package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PatchProjectsRequest extends RequestRestBase {
    public PatchProjectsRequest(int ID){
        requestService = "/api/rest/projects/" + ID;
        method = Method.PATCH;
    }

    public void setJsonBodyUsingJsonFile(
                            String idProjects,
                            String nameProjects,
                            String enabled){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PatchProjectsJson.json").
                                replace("$idProjects", idProjects).
                                replace("$nameProjects", nameProjects ).
                                replace("$enabled", enabled );
    }
}
