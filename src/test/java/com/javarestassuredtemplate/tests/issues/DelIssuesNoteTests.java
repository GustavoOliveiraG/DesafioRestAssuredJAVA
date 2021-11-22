package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.issues.DelIssuesNoteRequest;
import com.javarestassuredtemplate.requests.issues.DelIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesNoteSteps;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DelIssuesNoteTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesSteps postIssuesSteps;
    PostIssuesNoteSteps postIssuesNoteSteps;
    DelIssuesNoteRequest delIssuesNoteRequest;

    @Test
    public void apagarNotanoBugcomSucesso(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesNoteSteps = new PostIssuesNoteSteps();

        //Parâmetros
        int idBug = postIssuesSteps.cadastrarBugIssues();
        int idBugNote = postIssuesNoteSteps.cadastrarIssuesNote(idBug);
        int statusCodeEsperado01 = HttpStatus.SC_OK;

        //Fluxo
        delIssuesNoteRequest = new DelIssuesNoteRequest(idBug,idBugNote);
        Response response = delIssuesNoteRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);

    }

    @Test
    public void naoApagarNotanoBugIdInvalido(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesNoteSteps = new PostIssuesNoteSteps();

        //Parâmetros
        int idBugOld = postIssuesSteps.cadastrarBugIssues();
        int idBug = idBugOld + 100;

        int idBugNote = postIssuesNoteSteps.cadastrarIssuesNote(idBugOld);
        int statusCodeEsperado01 = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        delIssuesNoteRequest = new DelIssuesNoteRequest(idBug,idBugNote);
        Response response = delIssuesNoteRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);

    }



}
