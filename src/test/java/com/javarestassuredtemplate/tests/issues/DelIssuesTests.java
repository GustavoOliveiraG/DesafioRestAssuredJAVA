package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.issues.DelIssuesRequest;
import com.javarestassuredtemplate.requests.issues.PatchIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DelIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesSteps postIssuesSteps;
    DelIssuesRequest delIssuesRequest;

    @Test
    public void apagarBugcomSucesso(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int idBug = postIssuesSteps.cadastrarBugIssues();
        int statusCodeEsperado01 = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        delIssuesRequest = new DelIssuesRequest(idBug);
        Response response = delIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

    @Test
    public void naoApagarBugIdInvalido(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int idBugold = postIssuesSteps.cadastrarBugIssues();
        int idBug = idBugold + 100;
        int statusCodeEsperado01 = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        delIssuesRequest = new DelIssuesRequest(idBug);
        Response response = delIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }


}
