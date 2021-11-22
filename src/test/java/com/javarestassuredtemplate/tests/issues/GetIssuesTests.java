package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.issues.GetIssuesRequest;
import com.javarestassuredtemplate.requests.issues.PostIssuesNoteRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesSteps postIssuesSteps;
    GetIssuesRequest getIssuesRequest;


    @Test
    public void consultarBugcomSucesso()  {
    //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

    //Parâmetros
        int idProject = postIssuesSteps.cadastrarBugIssues();
        String issues_project_name = "[Projeto 009 INSERT query via BANCO]";
        String issues_id = "["+Integer.toString(idProject)+"]";
        int statusCodeEsperado01 = HttpStatus.SC_OK;

    //Fluxo
        getIssuesRequest = new GetIssuesRequest(idProject);
        Response response = getIssuesRequest.executeRequest2();

    //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        softAssert.assertEquals(response.body().jsonPath().get("issues.id").toString(), issues_id, "Validação campo: issues.id");
        softAssert.assertEquals(response.body().jsonPath().get("issues.project.name").toString(), issues_project_name, "Validação campo: issues.project.name");
        softAssert.assertAll();
    }

    @Test
    public void consultaInvalidaBugnaoEncontrado()  {
    //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

    //Parâmetros
        int idProjectOld = postIssuesSteps.cadastrarBugIssues();
        int idProject = idProjectOld + 100;
        String issues_id = Integer.toString(idProject);
        String message = "Issue #" +issues_id+ " not found";
        int statusCodeEsperado01 = HttpStatus.SC_NOT_FOUND;

    //Fluxo
        getIssuesRequest = new GetIssuesRequest(idProject);
        Response response = getIssuesRequest.executeRequest2();

    //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();
    }


}
