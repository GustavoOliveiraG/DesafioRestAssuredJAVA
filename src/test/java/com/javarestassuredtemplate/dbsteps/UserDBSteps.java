package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;


public class UserDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    public static void apagarUsuarios(){
        String query1 = GeneralUtils.readFileToAString(queriesPath + "deleteUsuariosQuery.sql");
        DBUtils.executeQuery(query1);
    }

    public static void cadastrarUsuarios(){
        String query2 = GeneralUtils.readFileToAString(queriesPath + "insertUsuariosQuery.sql");
        DBUtils.executeQuery(query2);
    }

    public static ArrayList<String> selectUsuarioADM(){
        ArrayList <String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "selectUsuarioADMQuery.sql");
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