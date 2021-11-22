package com.javarestassuredtemplate.requests.issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostIssuesNoteRequest extends RequestRestBase {
    public PostIssuesNoteRequest(int idBug){
        requestService = "/api/rest/issues/" + idBug + "/notes";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
            String test_note_name,
            String view_state){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/issues/PostIssuesNoteJson.json").
                replace("$test_note_name", test_note_name).
                replace("$view_state", view_state );
    }


}
