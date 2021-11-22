package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;


public class IssuesDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    public static void apagarBugs(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteIssuesQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void apagarHistoryBugs(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteIssuesHistoryQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void apagarTextBugs(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteIssuesTextQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static ArrayList<String> selectBugID(){
        ArrayList <String> dadosID;
        String query = GeneralUtils.readFileToAString(queriesPath + "selectIssuesQuery.sql");
        dadosID = DBUtils.getQueryResult(query);
        return dadosID;
    }

}