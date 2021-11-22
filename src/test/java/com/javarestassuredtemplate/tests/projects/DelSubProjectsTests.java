package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.DelProjectsRequest;
import com.javarestassuredtemplate.requests.projects.DelSubProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DelSubProjectsTests extends TestBase {
    DelSubProjectsRequest delSubProjectsRequest;

    @Test
    public void deletaSubProjetocomSucesso(){
        //Parâmetros
        int IDProject =  7;
        int IDSubProject = 3;
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        delSubProjectsRequest = new DelSubProjectsRequest(IDProject, IDSubProject);
        Response response = delSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoDeletaSubProjetoInexistente(){
        //Parâmetros
        int IDProject =  7;
        int IDSubProject = 999999;
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        delSubProjectsRequest = new DelSubProjectsRequest(IDProject, IDSubProject);
        Response response = delSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoDeletaSubProjetoProjetoInexistente(){
        //Parâmetros
        int IDProject =  999999;
        int IDSubProject = 3;
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        delSubProjectsRequest = new DelSubProjectsRequest(IDProject, IDSubProject);
        Response response = delSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }


}
