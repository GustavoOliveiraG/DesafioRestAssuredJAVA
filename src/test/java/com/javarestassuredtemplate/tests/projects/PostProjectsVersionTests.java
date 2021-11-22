package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.PostProjectsRequest;
import com.javarestassuredtemplate.requests.projects.PostProjectsVersionRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostProjectsVersionTests extends TestBase {
    PostProjectsVersionRequest postProjectsVersionRequest;
    SoftAssert softAssert;

    @Test
    public void cadastrarVersaonoProjeto(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int ID = 1;
        String projectVersion = "V.1.99.99";
        String projectDescription = "Versao Teste API";
        String projectRelease ="1";
        String projectObsolete = "0";
        String projectDate = "2021-09-03";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        postProjectsVersionRequest = new PostProjectsVersionRequest(ID);
        postProjectsVersionRequest.setJsonBodyUsingJsonFile(projectVersion, projectDescription, projectRelease, projectObsolete, projectDate);
        Response response = postProjectsVersionRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
    }

    @Test
    public void bloquearCadastrarVersaoIgual(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int ID = 1;
        String projectVersion = "V.1.00.00";
        String projectDescription = "Versao Igual a uma Existente";
        String projectRelease ="1";
        String projectObsolete = "0";
        String projectDate = "2021-09-04";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String menssage = "Version 'V.1.00.00' already exists";
        String code = "1600";

        //Fluxo
        postProjectsVersionRequest = new PostProjectsVersionRequest(ID);
        postProjectsVersionRequest.setJsonBodyUsingJsonFile(projectVersion, projectDescription, projectRelease, projectObsolete, projectDate);
        Response response = postProjectsVersionRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), menssage, "Validação campo: messagee");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();

    }

}
