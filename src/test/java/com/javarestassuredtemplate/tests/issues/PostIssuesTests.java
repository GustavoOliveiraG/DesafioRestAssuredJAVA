package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.IssuesDBSteps;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.issues.PostIssuesRequest;
import com.javarestassuredtemplate.requests.user.PostUserRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import com.javarestassuredtemplate.utils.ExcelUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.matchesRegex;

public class PostIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesRequest postIssuesRequest;
    PostIssuesSteps postIssuesSteps;

    @Test
    public void cadastrarBugcomSucesso()  {
    //Chamadas
        softAssert = new SoftAssert();

    //Parâmetros
        String summary = "Teste Cadastro de problema";
        String description = "Utilizado para Automatizar os testes";
        String category_name = "General";
        String project_name = "Projeto 008 INSERT query via BANCO";
        int statusCodeEsperado01 = HttpStatus.SC_CREATED;

    //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

    //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        softAssert.assertEquals(response.body().jsonPath().get("issue.description").toString(), description, "Validação campo: issue.description");
        softAssert.assertAll();

    }

    @Test
    public void naoCadastrarBugcomProjetoInexistente()  {
    //Chamadas
        softAssert = new SoftAssert();

    //Parâmetros
        String summary = "Teste Cadastro de problema";
        String description = "Utilizado para Automatizar os testes";
        String category_name = "General";
        String project_name = "Projeto 999 INSERT query via BANCO";
        String message = "Project not specified";
        int statusCodeEsperado01 = HttpStatus.SC_BAD_REQUEST;

    //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

    //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();

    }

}
