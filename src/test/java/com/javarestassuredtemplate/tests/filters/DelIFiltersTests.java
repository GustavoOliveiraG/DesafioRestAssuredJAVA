package com.javarestassuredtemplate.tests.filters;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.filters.DelIFiltersRequest;
import com.javarestassuredtemplate.requests.issues.DelIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DelIFiltersTests extends TestBase {
    SoftAssert softAssert;
    DelIFiltersRequest delIFiltersRequest;

    @Test
    public void apagarFiltrocomSucesso(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado01 = HttpStatus.SC_NO_CONTENT;
        String id = "4";

        //Fluxo
        delIFiltersRequest = new DelIFiltersRequest(id);
        Response response = delIFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

    @Test
    public void apagarFiltroIDInvalido(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado01 = HttpStatus.SC_NOT_FOUND;
        String id = "14";

        //Fluxo
        delIFiltersRequest = new DelIFiltersRequest(id);
        Response response = delIFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

    @Test
    public void apagarFiltroIDnulo(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado01 = HttpStatus.SC_METHOD_NOT_ALLOWED;
        String id = "";

        //Fluxo
        delIFiltersRequest = new DelIFiltersRequest(id);
        Response response = delIFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

}
