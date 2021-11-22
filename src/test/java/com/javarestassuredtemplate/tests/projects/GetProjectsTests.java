package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ProjectsDBSteps;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.projects.GetProjectsRequest;
import com.javarestassuredtemplate.requests.projects.PostProjectsRequest;
import com.javarestassuredtemplate.requests.user.GetUserRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;


public class GetProjectsTests extends TestBase {

    @Test
    public void buscarProjetoCadsatrado(){
        SoftAssert softAssert = new SoftAssert();

        //POST
        //Parâmetros
        String projectName = "Projeto004 Cadastrado para GET";
        int statusID = 50;
        String statusName ="stable";
        String descriptionText = "Projeto 004 Criado GET Test ";
        int enableID = 0;
        int viewID = 10;
        String viewName = "public";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        PostProjectsRequest postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response =postProjectsRequest.executeRequest2();

        //Asserções POST
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "Validação campo: statusCodeEsperado");

        //Consulta ID Projeto Via Query
        ArrayList<String> list = ProjectsDBSteps.selectProjetoID();
        String idQuery = list.get(0);
        int ID = Integer.parseInt(idQuery);

        //GET
        //Parâmetros
        int statusCodeEsperadoGET = HttpStatus.SC_OK;

        //Fluxo
        GetProjectsRequest getProjectsRequest = new GetProjectsRequest(ID);
        Response responseGET = getProjectsRequest.executeRequest2();

        //Asserções GET
        Assert.assertEquals(responseGET.statusCode(), statusCodeEsperadoGET,"Validação campo: statusCodeEsperadoGET");
        softAssert.assertEquals(responseGET.body().jsonPath().get("projects.id[0]").toString(), idQuery, "Validação campo: projects.id[0]");
        softAssert.assertEquals(responseGET.body().jsonPath().get("projects.name[0]").toString(), projectName, "Validação campo: projects.name[0]");
        softAssert.assertAll();
    }

    @Test
    public void buscarProjetoNaoCadsatrado(){
        SoftAssert softAssert = new SoftAssert();
        //GET
        //Parâmetros
        int statusCodeEsperadoGET = HttpStatus.SC_NOT_FOUND;
        int ID = -1;

        String message = "Project #-1 not found";
        String code = "700";

        //Fluxo
        GetProjectsRequest getProjectsRequest = new GetProjectsRequest(ID);
        Response response = getProjectsRequest.executeRequest2();

        //Asserções GET
        Assert.assertEquals(response.statusCode(), statusCodeEsperadoGET,"Validação campo: statusCodeEsperado");
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();
    }

}
