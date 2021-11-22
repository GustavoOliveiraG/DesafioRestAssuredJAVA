package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.user.GetUserRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

public class GetUserTests extends TestBase {
    GetUserRequest getUserRequest;

    @Test
    public void buscarUsuarioADMCadsatrado(){
        //Parâmetros
        String name = "administrator";
        String email = "root@localhost";
        int accessId = 90;
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getUserRequest = new GetUserRequest();
        ValidatableResponse response = getUserRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado);
        response.body("name",equalTo(name),
                "email",equalTo(email),
                "access_level.id",equalTo(accessId));
    }

    @Test
    public void buscarUsuarioADMviaQuery(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        ArrayList<String> list = UserDBSteps.selectUsuarioADM();
        String id = list.get(0);
        String name = list.get(1);
        String email = list.get(2);
        String accessLevel = list.get(3);
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getUserRequest = new GetUserRequest();
        Response response = getUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), id, "Validação campo: user.id");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name, "Validação campo: user.name");
        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação campo: user.email");
        softAssert.assertEquals(response.body().jsonPath().get("access_level.id").toString(), accessLevel, "Validação campo: access_level.id");
        softAssert.assertAll();
    }


}
