package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.issues.PostIssuesFilesRequest;
import com.javarestassuredtemplate.requests.issues.PostIssuesNoteRequest;
import com.javarestassuredtemplate.requests.issues.PostIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesFilesTests extends TestBase {
    PostIssuesSteps postIssuesSteps;
    PostIssuesFilesRequest postIssuesFilesRequest;


    @Test
    public void cadastrarAnexonoBugcomSucesso()  {
        //Chamadas
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int idBug = postIssuesSteps.cadastrarBugIssues();
        String name = "test.txt";
        String content = "VGhpcyBpcyBhIFRFU1QuDQpUaGlzIGlzIGEgVEVTVC4NClRoaXMgaXMgYSBURVNULg0KVGhpcyBpcyBhIFRFU1QuDQpUaGlzIGlzIGEgVEVTVC4=";
        int statusCodeEsperado01 = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesFilesRequest = new PostIssuesFilesRequest(idBug);
        postIssuesFilesRequest.setJsonBodyUsingJsonFile(name,content);
        Response response = postIssuesFilesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

}
