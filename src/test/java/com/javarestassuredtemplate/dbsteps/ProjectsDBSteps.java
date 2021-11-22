package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;


public class ProjectsDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    public static void apagarProjetos(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarProjeto(){
        String query = GeneralUtils.readFileToAString(queriesPath + "insertProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void apagarProjetosVersion(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteProjetosVersionQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarProjetoVersion(){
        String query = GeneralUtils.readFileToAString(queriesPath + "insertProjetosVersionQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void apagarSubProjetos(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteSubProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarSubProjetos(){
        String query = GeneralUtils.readFileToAString(queriesPath + "insertSubProjetosQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static ArrayList<String> selectProjetoID(){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "selectProjetoQuery.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

/* Exemplo a Analisar
    public static String retornaSenhaDoUsuario(String userName){
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaSenhaUsuarioQuery");
        query.replace("$nome", userName);

        return DBUtils.getQueryResult(query).get(0);
    }

 */


}