package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.projects.PostProjectsRequest;
import com.javarestassuredtemplate.requests.user.PostUserRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.equalTo;

public class PostProjectsTests extends TestBase {
    PostProjectsRequest postProjectsRequest;
    SoftAssert softAssert;

    @Test
    public void cadastrarProjetoNovocomSucesso(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Projeto001";
        int statusID = 50;
        String statusName ="stable";
        String descriptionText = "Projeto 001 Criado para os testes Automação";
        int enableID = 0;
        int viewID = 10;
        String viewName = "public";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response =postProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("project.name").toString(), projectName, "Validação campo: project.name");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.id"), statusID, "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.name").toString(), statusName, "Validação campo: project.status.name");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.id"), viewID, "Validação campo: project.view_state.id");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.name").toString(), "public", "Validação campo: project.view_state.name");
        softAssert.assertAll();



    }

    @Test
    public void bloquearCadastrarProjetoNomeIgual(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Projeto 001 INSERT query via BANCO";
        int statusID = 50;
        String statusName ="stable";
        String descriptionText = "Projeto Tentativa de Cadastro Nome Igual";
        int enableID = 0;
        int viewID = 10;
        String viewName = "public";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response =postProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().htmlPath().get().toString().contains("Fatal error"), "Validação campo: HTML");
        softAssert.assertAll();

    }

    @Test
    public void bloquearCadastrarProjetoSemNome(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "";
        int statusID = 50;
        String statusName ="stable";
        String descriptionText = "Projeto Tentativa de Cadastro Nome Igual";
        int enableID = 0;
        int viewID = 10;
        String viewName = "public";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response =postProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().htmlPath().get().toString().contains("Fatal error"), "Validação campo: HTML");
        softAssert.assertEquals(response.body().htmlPath().get().toString(), "nullFatal error/var/www/html/core/project_api.php341null", "Validação campo: HTML");
        softAssert.assertAll();

    }

}
