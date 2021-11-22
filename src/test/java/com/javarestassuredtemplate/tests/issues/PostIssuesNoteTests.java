package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.IssuesDBSteps;
import com.javarestassuredtemplate.requests.issues.PostIssuesNoteRequest;
import com.javarestassuredtemplate.requests.issues.PostIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class PostIssuesNoteTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesNoteRequest postIssuesNoteRequest;
    PostIssuesSteps postIssuesSteps;

    @Test
    public void cadastrarNotaBugcomSucesso()  {
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int IDProject = postIssuesSteps.cadastrarBugIssues();
        String test_note_name = "Anotação 001 do BUG para Teste Automatizado";
        String view_state = "public";
        int statusCodeEsperado01 = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesNoteRequest = new PostIssuesNoteRequest(IDProject);
        postIssuesNoteRequest.setJsonBodyUsingJsonFile(test_note_name, view_state);
        Response response = postIssuesNoteRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

    @Test
    public void cadastrarVariasNotaMesmoBug()  {
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int IDProject = postIssuesSteps.cadastrarBugIssues();

        for (int i = 10; i < 15; i++) {
            String test_note_name = "Anotação "+ i +" do BUG para Teste Automatizado";
            String view_state = "public";
            int statusCodeEsperado01 = HttpStatus.SC_CREATED;

            //Fluxo
            postIssuesNoteRequest = new PostIssuesNoteRequest(IDProject);
            postIssuesNoteRequest.setJsonBodyUsingJsonFile(test_note_name, view_state);
            Response response = postIssuesNoteRequest.executeRequest2();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        }
    }

    @Test
    public void naocadastrarNotaBug()  {
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int IDProject = postIssuesSteps.cadastrarBugIssues();
        int newIDProject = IDProject + 100;
        String test_note_name = "Anotação 002 do BUG para Teste Automatizado";
        String view_state = "public";
        String message = "Issue #"+newIDProject +" not found";
        int statusCodeEsperado01 = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        postIssuesNoteRequest = new PostIssuesNoteRequest(newIDProject);
        postIssuesNoteRequest.setJsonBodyUsingJsonFile(test_note_name, view_state);
        Response response = postIssuesNoteRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();
    }

}
