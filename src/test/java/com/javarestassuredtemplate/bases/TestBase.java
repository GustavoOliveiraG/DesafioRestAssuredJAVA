package com.javarestassuredtemplate.bases;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.dbsteps.FiltersDBSteps;
import com.javarestassuredtemplate.dbsteps.IssuesDBSteps;
import com.javarestassuredtemplate.dbsteps.ProjectsDBSteps;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import com.javarestassuredtemplate.utils.ExtentReportsUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public abstract class TestBase {

    @BeforeSuite
    public void beforSuite(){
        new GlobalParameters();
        ExtentReportsUtils.createReport();

        //DB
        UserDBSteps.apagarUsuarios();
        ProjectsDBSteps.apagarProjetos();
        ProjectsDBSteps.apagarProjetosVersion();
        ProjectsDBSteps.apagarSubProjetos();
        IssuesDBSteps.apagarBugs();
        IssuesDBSteps.apagarHistoryBugs();
        IssuesDBSteps.apagarTextBugs();

        UserDBSteps.cadastrarUsuarios();
        ProjectsDBSteps.cadastrarProjeto();
        ProjectsDBSteps.cadastrarProjetoVersion();
        ProjectsDBSteps.cadastrarSubProjetos();

        FiltersDBSteps.apagarFiltros();
        FiltersDBSteps.cadastrarFiltros();



        //AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); ==> caso a geração de token deva ser feita quando iniciar a bateria de testes
    }

    @BeforeMethod
    public void beforeTest(Method method){
        ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());
        //System.out.println("Thread de Teste: " + Thread.currentThread().getId() + " " + method.getName());
        //AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); ==> caso a geração de token deva ser feita antes de iniciar cada teste
    }

    @AfterMethod
    public void afterTest(ITestResult result){
        ExtentReportsUtils.addTestResult(result);
    }

    @AfterSuite
    public void afterSuite(){
        ExtentReportsUtils.generateReport();
    }
}
