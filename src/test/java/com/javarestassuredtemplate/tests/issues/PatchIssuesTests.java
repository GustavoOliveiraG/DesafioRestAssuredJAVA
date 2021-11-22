package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.issues.GetIssuesRequest;
import com.javarestassuredtemplate.requests.issues.PatchIssuesRequest;
import com.javarestassuredtemplate.requests.projects.PatchProjectsRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatchIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesSteps postIssuesSteps;
    PatchIssuesRequest patchIssuesRequest;

    @Test
    public void atualizarStatusdoBug(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int idBug = postIssuesSteps.cadastrarBugIssues();
        String status_name = "assigned";
        int statusCodeEsperado01 = HttpStatus.SC_OK;

        //Fluxo
        patchIssuesRequest = new PatchIssuesRequest(idBug);
        patchIssuesRequest.setJsonBodyUsingJsonFile(status_name);
        Response response = patchIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);

    }

    @Test
    public void naoAtualizarStatusBugIDInvalido(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int idBugold = postIssuesSteps.cadastrarBugIssues();
        int idBug = idBugold + 100;
        String status_name = "assigned";
        int statusCodeEsperado01 = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchIssuesRequest = new PatchIssuesRequest(idBug);
        patchIssuesRequest.setJsonBodyUsingJsonFile(status_name);
        Response response = patchIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);

    }
}
