package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.bases.TestBase;

import com.javarestassuredtemplate.requests.user.PostUserRequest;
import com.javarestassuredtemplate.utils.ExcelUtils;
import com.javarestassuredtemplate.utils.ReadingCSVUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;

public class PostUserTests extends TestBase {
    PostUserRequest postUserRequest;
    ExcelUtils excelUtils;
    SoftAssert softAssert;

    @Test
    public void cadastrarUsuariocomSucesso()  {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String userName01 = "Usuario 001 API Automacao";
        String password01 = "123456";
        String nameReal01 = "Usuario 001 Base2";
        String email01 = "userautomacao001@Base2.com.br";
        int accessId01 = 40;
        String accessIdText01 = Integer.toString(accessId01);
        int enableId01 = 1;
        int protectedId01 = 0;
        int statusCodeEsperado01 = HttpStatus.SC_CREATED;

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyUsingJsonFile(userName01, password01, nameReal01, email01, accessId01, enableId01, protectedId01 );
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), userName01, "Validação campo: user.name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), nameReal01, "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email01, "Validação campo: user.email");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.id").toString(), accessIdText01, "Validação campo: user.access_level.id");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), "updater", "Validação campo: user.access_level.name");
        softAssert.assertAll();
    }

    //Validação via VALIDATABLERESPONSE
    @Test
    public void cadastrarUsuarioValidacaoRegexEmailValidaResponse() {
        //Parâmetros
        String userName02 = "Usuario 002 API Automacao";
        String password02 = "123456";
        String nameReal02 = "Usuario 002 Base2";
        String email02 = "userautomacao002@Base2.com.br";
        int accessId02 = 40;
        int enableId02 = 1;
        int protectedId02 = 0;
        int statusCodeEsperado02 = HttpStatus.SC_CREATED;

        //Variaveis
        String regex = "^[A-Za-z0-9+_-]+@[A-Za-z0-9.-]+$";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyUsingJsonFile(userName02, password02, nameReal02, email02, accessId02, enableId02, protectedId02 );
        ValidatableResponse response = postUserRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado02);
        response.body("user.name",equalTo(userName02),
                "user.real_name", equalTo(nameReal02),
                "user.email", equalTo(email02),
                "user.email", matchesRegex(regex),
                "user.access_level.id", equalTo(accessId02),
                "user.access_level.name",equalTo("updater"));
    }

    @Test
    public void cadastrarUsuarioValidacaoRegexEmail() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parametros
        String userName04 = "Usuario 003 API Automacao";
        String password04 = "123456789";
        String nameReal04 = "Usuario 003 Base2";
        String email04 = "userautomacao003@Base2.com.br";
        int accessId04 = 10;
        int enableId04 = 1;
        int protectedId04 = 0;
        int statusCodeEsperado04 = HttpStatus.SC_CREATED;

        //Variaveis
        String regex02 = "^[A-Za-z0-9+_-]+@[A-Za-z0-9.-]+$";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyUsingJsonFile(userName04, password04, nameReal04, email04, accessId04, enableId04, protectedId04 );
        Response response = postUserRequest.executeRequest2();

        ValidatableResponse response1 = postUserRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado04);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), userName04, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), nameReal04, "Validação realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email04, "Validação email");
        softAssert.assertTrue(response.body().jsonPath().get("user.email").toString().matches(regex02), "Validação email regex");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), "viewer", "Validação accessLevel name");
        softAssert.assertAll();

    }

    @Test(dataProvider = "dataAddUserCSVProvider", dataProviderClass = ReadingCSVUtils.class)
    public void cadastrarVariosUsuarioscomSucessoDDTCSV(String[] userData) {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String csvUserName = userData[0];
        String csvPassword = userData[1];
        String csvNameReal = userData[2];
        String csvEmail = userData[3];
        String csvAccess = userData[4];
        int csvAccessID = Integer.parseInt(csvAccess);
        String csvEnable = userData[5];
        int csvEnableID = Integer.parseInt(csvEnable);
        String csvprotected = userData[6];
        int csvprotectedId = Integer.parseInt(csvprotected);
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyUsingJsonFile(csvUserName, csvPassword, csvNameReal, csvEmail, csvAccessID, csvEnableID, csvprotectedId );
        Response response = postUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), csvUserName, "Validação campo: user.name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), csvNameReal, "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), csvEmail, "Validação campo: user.email");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.id").toString(), csvAccess, "Validação campo: user.access_level.id");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), "updater", "Validação campo: user.access_level.name");
        softAssert.assertAll();
    }

    @Test
    public void cadastrarVariosUsuarioscomSucessoDDTExcel() throws IOException {
        //Chamadas
        softAssert = new SoftAssert();

        //Parametros Excel
        String excelPath = "src/test/java/resources/DadosProjetos.xlsx";
        String sheetName = "Planilha";
        excelUtils  = new ExcelUtils(excelPath, sheetName);
        int tam = ExcelUtils.getRowCount();

        for (int x = 1; x < tam; x++) {
            //Parâmetros
            String xlsxUserName = ExcelUtils.getCellData(x, 0).toString();
            String xlsxPassword = ExcelUtils.getCellData(x, 1).toString();
            String xlsxNameReal = ExcelUtils.getCellData(x, 2).toString();
            String xlsxEmail = ExcelUtils.getCellData(x, 3).toString();
            String xlsxAccess = ExcelUtils.getCellData(x, 4).toString();
            int xlsxAccessID = Integer.parseInt(xlsxAccess);
            String xlsxEnable = ExcelUtils.getCellData(x, 5).toString();
            int xlsxEnableID = Integer.parseInt(xlsxEnable);
            String xlsxprotected = ExcelUtils.getCellData(x, 6).toString();
            int xlsxprotectedID = Integer.parseInt(xlsxprotected);
            int statusCodeEsperado = HttpStatus.SC_CREATED;

            //Fluxo
            postUserRequest = new PostUserRequest();
            postUserRequest.setJsonBodyUsingJsonFile(xlsxUserName, xlsxPassword, xlsxNameReal, xlsxEmail, xlsxAccessID, xlsxEnableID, xlsxprotectedID);
            Response response = postUserRequest.executeRequest2();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), xlsxUserName, "Validação campo: user.name");
            softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), xlsxNameReal, "Validação campo: user.realName");
            softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), xlsxEmail, "Validação campo: user.email");
            softAssert.assertEquals(response.body().jsonPath().get("user.access_level.id").toString(), xlsxAccess, "Validação campo: user.access_level.id");
            softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), "updater", "Validação campo: user.access_level.name");
            softAssert.assertAll();
        }
    }

    @Test
    public void bloquearCadastroNomerepetido() {
        //Parâmetros
        String userName03 = "Usuario Cadastro Igual";
        String password03 = "123456";
        String nameReal03 = "Usuario Igual";
        String email03 = "user@base2.com.br";
        int accessId03 = 10;
        int enableId03 = 1;
        int protectedId03 = 0;
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "Username '" +userName03+ "' already used.";
        String localized = "That username is already being used. Please go back and select another one.";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyUsingJsonFile(userName03, password03, nameReal03, email03, accessId03, enableId03, protectedId03 );
        ValidatableResponse response = postUserRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado);
        response.body("message",equalTo(mensagem),
                "localized", equalTo(localized));

    }

    @Test
    public void bloquearCadastroEmailrepetido() {
        //Parâmetros
        String userName03 = "Usuario Cadastro Igual 001";
        String password03 = "123456";
        String nameReal03 = "Usuario Igual";
        String email03 = "user@base2.com.br";
        int accessId03 = 10;
        int enableId03 = 1;
        int protectedId03 = 0;
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "Email '" +email03+ "' already used.";
        String localized = "That email is already being used. Please go back and select another one.";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyUsingJsonFile(userName03, password03, nameReal03, email03, accessId03, enableId03, protectedId03 );
        ValidatableResponse response = postUserRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado);
        response.body("message",equalTo(mensagem),
                "localized", equalTo(localized));

    }


    //Objeto REGEX (Exemplo)
    public void regexEmail() {
        String regex = "^[A-Za-z0-9+_-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("gustavo.gontijo@base2.com.br");
        boolean matchFound = matcher.find();

        if(matchFound) {
            System.out.println("Email Valido");
        } else {
            System.out.println("Email Invalido");
        }
    }




}
