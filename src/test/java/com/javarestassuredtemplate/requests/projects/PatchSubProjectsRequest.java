package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PatchSubProjectsRequest extends RequestRestBase {
    public PatchSubProjectsRequest(int IDProj, int IDSubProj){
        requestService = "/api/rest/projects/" + IDProj + "/subprojects/" + IDSubProj;
        method = Method.PATCH;
    }

    public void setJsonBodyUsingJsonFile(
                            String Subprojectname,
                            String inherit_parent){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PatchSubProjectsJson.json").
                                replace("$Subprojectname", Subprojectname).
                                replace("$inherit_parent", inherit_parent );
    }
}
