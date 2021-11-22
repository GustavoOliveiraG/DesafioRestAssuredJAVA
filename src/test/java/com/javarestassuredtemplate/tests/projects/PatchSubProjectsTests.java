package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.PatchSubProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchSubProjectsTests extends TestBase {
    PatchSubProjectsRequest patchSubProjectsRequest;

    @Test
    public void atualizarHerancaSubProjetoSucesso(){
        //Parâmetros
        int IDProj = 6;
        int IDSubProj = 3;
        String subprojectname = "ProjetoName";
        String inherit_parent = "0";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        patchSubProjectsRequest = new PatchSubProjectsRequest(IDProj, IDSubProj);
        patchSubProjectsRequest.setJsonBodyUsingJsonFile(subprojectname, inherit_parent);
        Response response = patchSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validação do Campo: status_code");
    }

    @Test
    public void naoAtualizarHerancaSubProjetoInexistente(){
        //Parâmetros
        int IDProj = 6;
        int IDSubProj = 9999;
        String subprojectname = "ProjetoName";
        String inherit_parent = "0";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchSubProjectsRequest = new PatchSubProjectsRequest(IDProj, IDSubProj);
        patchSubProjectsRequest.setJsonBodyUsingJsonFile(subprojectname, inherit_parent);
        Response response = patchSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validação do Campo: status_code");
    }

    @Test
    public void naoAtualizarHerancaProjetoInexistente(){
        //Parâmetros
        int IDProj = 99999;
        int IDSubProj = 3;
        String subprojectname = "ProjetoName";
        String inherit_parent = "0";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchSubProjectsRequest = new PatchSubProjectsRequest(IDProj, IDSubProj);
        patchSubProjectsRequest.setJsonBodyUsingJsonFile(subprojectname, inherit_parent);
        Response response = patchSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validação do Campo: status_code");
    }
}
