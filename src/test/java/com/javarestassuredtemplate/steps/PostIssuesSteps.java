package com.javarestassuredtemplate.steps;


import com.javarestassuredtemplate.requests.issues.PostIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class PostIssuesSteps {

    public static int cadastrarBugIssues()  {
        //Chamadas
        PostIssuesRequest postIssuesRequest;

        //Parâmetros
        String summary = "Teste Cadastro de Issues e Complementos";
        String description = "Utilizado para Issues e Complementos";
        String category_name = "General";
        String project_name = "Projeto 009 INSERT query via BANCO";
        int statusCodeEsperado01 = HttpStatus.SC_CREATED;

     //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);

        //Retornos
        int idBug = response.body().jsonPath().get("issue.id");
        return idBug;
    }


}

