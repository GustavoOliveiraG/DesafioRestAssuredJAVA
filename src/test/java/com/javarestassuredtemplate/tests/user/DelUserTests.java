package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.user.DelUserRequest;
import com.javarestassuredtemplate.requests.user.GetUserRequest;
import com.javarestassuredtemplate.requests.user.PostUserRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class DelUserTests  extends TestBase {
    DelUserRequest delUserRequest;

    @Test
    public void deletaUsuariocomSucesso(){
        //Parâmetros
        int ID = 16;
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        delUserRequest = new DelUserRequest(ID);
        Response response = delUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void bloquearApagarUsuarioProtegido(){
        //Parâmetros
        int ID = 17;
        int statusCodeEsperado = HttpStatus.SC_FORBIDDEN;

        //Fluxo
        delUserRequest = new DelUserRequest(ID);
        Response response = delUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoApagarUsuarioInexistente(){
        //Parâmetros
        int ID = 9999999;
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        delUserRequest = new DelUserRequest(ID);
        Response response = delUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

}
