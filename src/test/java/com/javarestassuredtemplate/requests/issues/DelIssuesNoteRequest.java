package com.javarestassuredtemplate.requests.issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelIssuesNoteRequest extends RequestRestBase {
    public DelIssuesNoteRequest(int idBug, int idBudNote){
        requestService = "/api/rest/issues/" +idBug+ "/notes/" + idBudNote;
        method = Method.DELETE;
    }

}
