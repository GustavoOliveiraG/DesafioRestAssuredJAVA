package com.javarestassuredtemplate.steps;


import com.javarestassuredtemplate.requests.issues.PostIssuesNoteRequest;
import com.javarestassuredtemplate.requests.issues.PostIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesNoteSteps {
    public static int cadastrarIssuesNote(int idBug)  {
        //Chamadas
         PostIssuesNoteRequest postIssuesNoteRequest;

        //Parâmetros
        String test_note_name = "Anotação 001 do BUG para Teste Automatizado";
        String view_state = "public";
        int statusCodeEsperado01 = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesNoteRequest = new PostIssuesNoteRequest(idBug);
        postIssuesNoteRequest.setJsonBodyUsingJsonFile(test_note_name, view_state);
        Response response = postIssuesNoteRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);

        //Retornos
        int idBugNote = response.body().jsonPath().get("note.id");
        return idBugNote;
    }


}

